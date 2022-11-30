package com.lazylibs.component.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.lazylibs.component.ui.IContainer
import com.lazylibs.util.EventBusUtils

abstract class BaseVMDialogFragment<T : ViewDataBinding>(@LayoutRes val layoutId: Int) : DialogFragment(layoutId), IContainer {

    private var isFirstLoadData: Boolean = true
    lateinit var mBinding: T

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
    }

    open fun startObserver() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }


    override fun onResume() {
        super.onResume()
        loadData()
    }


    open fun loadData() {
        // view is nonnull or fragment is visible
        if (isVisible || isResumed) {
            if (isFirstLoadData) {
                initData()
                isFirstLoadData = false;
            } else if (!onceLoadData()) {
                initData()
            }
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

}