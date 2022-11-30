package com.chindeo.repository.util

import com.lazylibs.lifecycle.DisposableManager
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**

 * @Author chindeo_zcg
 * @Date 2022/10/8-17:18
 * @Email chrisSpringSmell@gmail.com
 */
class VisitUtils private constructor() {

    companion object {
        fun getInstance() = InstanceHelper.sSingle
    }

    object InstanceHelper {
        val sSingle = VisitUtils()
    }

    val tag:String = "VisitUtils"

    fun add(disposable: Disposable) {
        clear()
        DisposableManager.getInstance().add(tag, disposable)
    }

    fun clear(){
        DisposableManager.getInstance().clear(tag)
    }

    fun interval(back: () -> Unit){
        val interval = Flowable.interval(0, 60, TimeUnit.SECONDS)
            .doOnNext {
                back()
            }.subscribe()
        add(interval)
    }
}