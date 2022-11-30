package com.lazylibs.binding.web

import android.text.TextUtils
import android.webkit.WebView
import androidx.databinding.BindingAdapter

object ViewAdapter {
    @JvmStatic
    @BindingAdapter("render")
    fun loadHtml(webView: WebView, html: String?) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html!!, "text/html", "UTF-8", null)
        }
    }
    @JvmStatic
    @BindingAdapter("setWebUrl")
    fun loadUrl(webView: WebView, url: String?) {
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url!!)
        }
    }
}