package com.lazylibs.component.ui.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.lifecycleScope
import com.lazylibs.component.ui.IContainer
import com.lazylibs.component.util.FragmentUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 这里只传入 databinding 是由于 viewmodel要使用的话 可以直接通过koin注解
 */
abstract class BaseVMActivity<T : ViewDataBinding>(@LayoutRes val layoutId: Int) : AppCompatActivity(layoutId), IContainer {


    lateinit var mBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, layoutId)
        mBinding.lifecycleOwner = this
        init(savedInstanceState)
        initData()
        startObserver()
    }

    open fun startObserver() {

    }


    override fun onSupportNavigateUp(): Boolean {
        if (FragmentUtils.popBackStack(supportFragmentManager)) {
            return true
        }
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (FragmentUtils.popBackStack(supportFragmentManager)) {
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }
    open fun isInitialized() =  ::mBinding.isInitialized

    fun launchUI(block: suspend CoroutineScope.() -> Unit) = lifecycleScope.launch { block() }
}