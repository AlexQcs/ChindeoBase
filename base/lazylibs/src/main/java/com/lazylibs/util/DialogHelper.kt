package com.lazylibs.util

import android.content.Context
import com.lazylibs.R
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.interfaces.OnConfirmListener
import com.lxj.xpopup.interfaces.OnInputConfirmListener

/**
 * @author Alwyn
 * @Date 2020/12/1
 * @Description
 */
object DialogHelper {
    fun showBaseDialog(
        context: Context,
        title: String,
        content: String,
        func: () -> Unit
    ): BasePopupView {
        return XPopup.Builder(context).asConfirm(title, content, OnConfirmListener(func)).show()
    }

    fun showVersionDialog(
        context: Context,
        title: String,
        content: String,
        func: () -> Unit
    ): BasePopupView {
        return XPopup.Builder(context).asConfirm(title, content, "取消","下载",OnConfirmListener(func),null,false).show()
    }



    fun showNoCancelDialog(
        context: Context,
        title: String,
        content: String,
        viewId: Int =R.layout._xpopup_center_impl_confirm,
        func: () -> Unit
    ): BasePopupView {
        return XPopup.Builder(context)
            .maxWidth((ScreenUtils.getScreenWidth(context) / 3))
            .asConfirm(title, content, "取消", "确定", OnConfirmListener(func), null, false,viewId).show()
    }

    fun showSureDialog(
        context: Context,
        title: String,
        content: String,
        func: () -> Unit
    ): BasePopupView {
        return XPopup.Builder(context)
            .asConfirm(title, content, "我再想想", "退出", OnConfirmListener(func), null, false).show()
    }

    fun showInputSureDialog(
        context: Context,
        title: String,
        content: String?,
        hint: String?,
        func: (text:String) -> Unit
    ): BasePopupView {
        return XPopup.Builder(context)
            .maxWidth(ScreenUtils.getScreenHeight(context) / 3)
            .maxHeight(ScreenUtils.getScreenHeight(context) / 2)
            .asInputConfirm(title,content,hint, OnInputConfirmListener(func))
            .show()
    }

}