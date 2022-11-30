package com.chindeo.repository.resources.x

import com.lazylibs.http.data.IBaseResponse
import com.lazylibs.http.data.ResponseThrowable
import kotlinx.coroutines.flow.*

/**
 * 基础的 Repository
 */
open class BaseRepository {



    /**
     * @param remoto 网络数据
     * @param local 本地数据
     * @param save 当网络请求成功后，保存数据等操作
     * @param isUseCache 是否使用缓存
     */
    suspend fun <T> cacheNetCall(
            remoto: suspend () -> IBaseResponse<T>,
            local: suspend () -> T?,
            save: suspend (T) -> Unit,
            isUseCache: (T?) -> Boolean = { true }
    ): T {
        val localData = local.invoke()
        return if (isUseCache(localData) && localData != null) localData
        else {
            remoto().let { net ->
                if (net.isSuccess()) net.data().also { save(it) }
                throw ResponseThrowable(net)
            }
        }
    }

    fun onCleared() {
    }


/*    private var tokenDis: Disposable? = null

    /**
     * 请求
     */
    suspend fun <T : Any> callRequest(
            call: suspend () -> ResultState<T>
    ): ResultState<T> {
        return try {
            call()
        } catch (e: Exception) {
            //这里统一处理异常
            e.printStackTrace()
            ResultState.Error(DealException.handlerException(e))
        }
    }

    /**
     * 处理返回结果
     */
    suspend fun <T : Any> handleResponse(
            response: HttpResultX<T>,
            successBlock: (suspend CoroutineScope.() -> Unit)? = null,
            errorBlock: (suspend CoroutineScope.() -> Unit)? = null
    ): ResultState<T> {
        return coroutineScope {
            if (response.code != HttpResultCode.SUCCESS) {
                //返回的不成功
                errorBlock?.let {
                    it()
                    if (response.code == 401 || response.code == 301) {
                        AccessTokenLiveData.update(null)
                        if (tokenDis == null) {
                            LogUtils.w("####token失效。重新获取token")
                            tokenDis = ConfigRepository.getInstance().initToken()
                                    .doFinally { tokenDis = null }
                                    .singleOrError()
                                    .subscribeWith(object : DisposableSingleObserver<CipherTokenBean?>() {
                                        override fun onSuccess(cipherTokenBean: CipherTokenBean) {
                                            LogUtils.d("*###重新获取token成功 -> " + cipherTokenBean.token)
                                            if (TextUtils.isEmpty(cipherTokenBean.token)) {
                                                return
                                            }
                                            AccessTokenLiveData.update(cipherTokenBean.token)
                                        }

                                        override fun onError(e: Throwable) {
                                            e.printStackTrace()
                                        }
                                    })
                        }
                    }
                }
                LogUtils.e("接口异常 code:${response.code} msg:${response.message} ")
                //结果回调
                ResultState.Error(
                        ResultException(
                                response.code.toString(),
                                response.message ?: ""
                        )
                )
            } else {
                //返回成功
                successBlock?.let { it() }
                //结果回调
                ResultState.Success(response.data)
            }
        }
    }

    suspend fun <T : Any> executeRequest(
            block: suspend () -> HttpResultX<T>,
            stateLiveData: StateLiveData<T>
    ) {
        var baseResp = HttpResultX<T>()
        try {
            baseResp.dataStatus = DataStatus.STATE_LOADING
            stateLiveData.postValue(baseResp)
            //将结果复制给baseResp
            baseResp = block.invoke()
            if (baseResp.code == HttpResultCode.SUCCESS) {
                baseResp.dataStatus = DataStatus.STATE_SUCCESS
            } else {
                //服务器请求错误
                baseResp.dataStatus = DataStatus.STATE_ERROR
                baseResp.exception = ResultException(
                        baseResp.code.toString(),
                        baseResp.message ?: ""
                )
            }
        } catch (e: Exception) {
            //非后台返回错误，捕获到的异常
            baseResp.dataStatus = DataStatus.STATE_ERROR
            baseResp.exception = DealException.handlerException(e)
        } finally {
            stateLiveData.postValue(baseResp)
        }
    }


    *//**
     * 方式二：结合Flow请求数据。
     * 根据Flow的不同请求状态，如onStart、onEmpty、onCompletion等设置baseResp.dataState状态值，
     * 最后通过stateLiveData分发给UI层。
     *
     * @param block api的请求方法
     * @param stateLiveData 每个请求传入相应的LiveData，主要负责网络状态的监听
     *//*
    suspend fun <T : Any> executeReqWithFlow(
            block: suspend () -> HttpResultX<T>,
            stateLiveData: StateLiveData<T>
    ) {
        var baseResp = HttpResultX<T>()
        flow {
            val respResult = block.invoke()
            baseResp = respResult
            emit(respResult)
        }
                .flowOn(Dispatchers.IO)
                .onStart {
                    baseResp.dataStatus = DataStatus.STATE_LOADING
                    stateLiveData.postValue(baseResp)
                }
                .catch { exception ->
                    run {
                        //非后台返回错误，捕获到的异常
                        baseResp.dataStatus = DataStatus.STATE_ERROR
                        baseResp.exception = DealException.handlerException(exception)
                        stateLiveData.postValue(baseResp)
                    }
                }
                .collect {
                    if (baseResp.code == 0) {
                        baseResp.dataStatus = DataStatus.STATE_SUCCESS
                    } else {
                        //服务器请求错误
                        baseResp.dataStatus = DataStatus.STATE_ERROR
                        baseResp.exception = ResultException(
                                baseResp.code.toString(),
                                baseResp.message ?: ""
                        )
                    }
                    stateLiveData.postValue(baseResp)
                }
    }
*/
}