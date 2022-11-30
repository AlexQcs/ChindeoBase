package com.lazylibs.component.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.lazylibs.component.BaseApplication
import com.lazylibs.lifecycle.data.LoadingDialogFragment
import com.lazylibs.util.EventBusUtils
import com.lazylibs.util.ToastUtils

abstract class BaseVMFragment<DB : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment(layoutId) {

    private lateinit var mActivity: AppCompatActivity
    private var mFragmentProvider: ViewModelProvider? = null
    private var mActivityProvider: ViewModelProvider? = null
    private var mApplicationProvider: ViewModelProvider? = null

    private var isFirstLoadData: Boolean = true
    lateinit var mBinding: DB

    private var dialog: LoadingDialogFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mActivity = context as AppCompatActivity
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(savedInstanceState)
        startObserver()
        if (isRegEventBus()) {
            EventBusUtils.register(this)
        }
    }

    abstract fun init(savedInstanceState: Bundle?)

    protected open fun <T : ViewModel?> getFragmentScopeViewModel(@NonNull modelClass: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(this)
        }
        return mFragmentProvider!!.get(modelClass)
    }

    protected open fun <T : ViewModel?> getFragmentScopeViewModel(@NonNull owner: ViewModelStoreOwner, @NonNull modelClass: Class<T>): T {
        if (mFragmentProvider == null) {
            mFragmentProvider = ViewModelProvider(owner)
        }
        return mFragmentProvider!!.get(modelClass)
    }

    protected open fun <T : ViewModel?> getActivityScopeViewModel(@NonNull modelClass: Class<T>): T {
        if (mActivityProvider == null) {
            mActivityProvider = ViewModelProvider(mActivity)
        }
        return mActivityProvider!!.get(modelClass)
    }

    protected open fun <T : ViewModel?> getApplicationScopeViewModel(@NonNull modelClass: Class<T>): T {
        if (mApplicationProvider == null) {
            mApplicationProvider = ViewModelProvider(mActivity.applicationContext as BaseApplication)
        }
        return mApplicationProvider!!.get(modelClass)
    }

    open fun initData() {

    }

    open fun startObserver() {

    }

    open fun isRegEventBus(): Boolean {
        return false
    }

    override fun onDestroy() {
        mBinding.unbind()
        if (isRegEventBus()) {
            EventBusUtils.unregister(this);
        }
        super.onDestroy()

    }


    override fun onResume() {
        super.onResume()
        loadData()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden && !onceLoadData()) {
            initData()
        }
    }

    open fun loadData() {
        // view is nonnull or fragment is visible
        if (isFirstLoadData) {
            initData()
            isFirstLoadData = false
        } else if (!onceLoadData()) {
            initData()
        }
    }

    /**
     * default true
     *
     * @return true:once loading false:loop loading
     */
    open fun onceLoadData(): Boolean {
        return true
    }

    open fun isInitialized() =  ::mBinding.isInitialized
}