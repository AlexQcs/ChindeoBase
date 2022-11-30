package com.lazylibs.component

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

open class BaseApplication : Application(), ViewModelStoreOwner {

    private var mAppViewModelStore: ViewModelStore? = null

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
    }

    @NonNull
    override fun getViewModelStore(): ViewModelStore = mAppViewModelStore!!
}
