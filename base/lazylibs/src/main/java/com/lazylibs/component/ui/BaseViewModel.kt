package com.lazylibs.component.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.viewModelScope
import com.lazylibs.http.data.ExceptionHandle
import com.lazylibs.http.data.IBaseResponse
import com.lazylibs.http.data.ResponseThrowable
import com.lazylibs.lifecycle.data.ILoadingState
import com.lazylibs.lifecycle.data.LoadingDialogState
import com.lazylibs.util.ToastUtils
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


open class BaseViewModel(
        application: Application
) : AndroidViewModel(application), LifecycleObserver {

    private val loadingContainer: ILoadingState by lazy { LoadingDialogState.create() }

    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    fun launchUI(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch { block() }

    /**
     * 用流的方式进行网络请求
     */
    fun <T> launchFlow(block: suspend () -> T): Flow<T> {
        return flow {
            emit(block())
        }
    }

    /**
     *  不过滤请求结果
     * @param block 请求体
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun launchGo(
            block: suspend CoroutineScope.() -> Unit,
            error: suspend CoroutineScope.(ResponseThrowable) -> Unit = {
                ToastUtils.showShort(getApplication(), "接口异常${it.code}:${it.errMsg}")
            },
            complete: suspend CoroutineScope.() -> Unit = {},
            isShowDialog: Boolean = true
    ) {
        loadingContainer.onComplete()
        if (isShowDialog) loadingContainer.onLoading()
        launchUI {
            handleException(
                    withContext(Dispatchers.IO) {
                        loadingContainer.onComplete()
                        block },
                    {
                        loadingContainer.onComplete()
                        error(it) },
                    {
                        loadingContainer.onComplete()
                        complete()
                    }
            )
        }
    }

    /**
     * 过滤请求结果，其他全抛异常
     * @param block 请求体
     * @param success 成功回调
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    fun <T> launchOnlyResult(
            block: suspend CoroutineScope.() -> IBaseResponse<T>,
            success: (T) -> Unit,
            error: (ResponseThrowable) -> Unit = {
                ToastUtils.showShort(getApplication(), "${it.code}:${it.errMsg}")
            },
            complete: () -> Unit = {},
            isShowDialog: Boolean = true
    ) {
        loadingContainer.onComplete()
        if (isShowDialog) loadingContainer.onLoading()
        launchUI {
            handleException(
                    {
                        withContext(Dispatchers.IO) {
                            block().let {
                                loadingContainer.onComplete()
                                if (it.isSuccess()) it.data()
                                else throw ResponseThrowable(it.code(), it.msg())
                            }
                        }.also {
                            success(it) }
                    },
                    {
                        loadingContainer.onComplete()
                        if (it.code!=1000){
                            Log.e(BaseViewModel::class.java.simpleName, "okResponse:  |||||||||| ${it.code} ${it.errMsg} ||||||||||||||||||||")
//                            loadingContainer.onError(it)
                            error(it)
                        }
                    },
                    {
                        loadingContainer.onComplete()
                        complete()
                    }
            )
        }
    }


    /**
     * 异常统一处理
     */
    suspend fun handleException(
            block: suspend CoroutineScope.() -> Unit,
            error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
            complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                block()
            } catch (e: Throwable) {
                e.printStackTrace()
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }




}