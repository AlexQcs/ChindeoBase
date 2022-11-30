package com.lazylibs.binding.web;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007J\u001a\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\bH\u0007\u00a8\u0006\u000b"}, d2 = {"Lcom/lazylibs/binding/web/ViewAdapter;", "", "()V", "loadHtml", "", "webView", "Landroid/webkit/WebView;", "html", "", "loadUrl", "url", "lazylibs_debug"})
public final class ViewAdapter {
    @org.jetbrains.annotations.NotNull()
    public static final com.lazylibs.binding.web.ViewAdapter INSTANCE = null;
    
    private ViewAdapter() {
        super();
    }
    
    @androidx.databinding.BindingAdapter(value = {"render"})
    @kotlin.jvm.JvmStatic()
    public static final void loadHtml(@org.jetbrains.annotations.NotNull()
    android.webkit.WebView webView, @org.jetbrains.annotations.Nullable()
    java.lang.String html) {
    }
    
    @androidx.databinding.BindingAdapter(value = {"setWebUrl"})
    @kotlin.jvm.JvmStatic()
    public static final void loadUrl(@org.jetbrains.annotations.NotNull()
    android.webkit.WebView webView, @org.jetbrains.annotations.Nullable()
    java.lang.String url) {
    }
}