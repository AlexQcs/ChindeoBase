package com.lazylibs.component;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0017J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/lazylibs/component/BaseApplication;", "Landroid/app/Application;", "Landroidx/lifecycle/ViewModelStoreOwner;", "()V", "mAppViewModelStore", "Landroidx/lifecycle/ViewModelStore;", "getViewModelStore", "onCreate", "", "lazylibs_debug"})
public class BaseApplication extends android.app.Application implements androidx.lifecycle.ViewModelStoreOwner {
    private androidx.lifecycle.ViewModelStore mAppViewModelStore;
    
    public BaseApplication() {
        super();
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    @java.lang.Override()
    public androidx.lifecycle.ViewModelStore getViewModelStore() {
        return null;
    }
}