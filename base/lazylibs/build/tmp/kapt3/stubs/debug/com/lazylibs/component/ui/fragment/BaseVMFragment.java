package com.lazylibs.component.ui.fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u000f\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J)\u0010\u0019\u001a\u0002H\u001a\"\n\b\u0001\u0010\u001a*\u0004\u0018\u00010\u001b2\u000e\b\u0001\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0014\u00a2\u0006\u0002\u0010\u001eJ)\u0010\u001f\u001a\u0002H\u001a\"\n\b\u0001\u0010\u001a*\u0004\u0018\u00010\u001b2\u000e\b\u0001\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0014\u00a2\u0006\u0002\u0010\u001eJ3\u0010 \u001a\u0002H\u001a\"\n\b\u0001\u0010\u001a*\u0004\u0018\u00010\u001b2\b\b\u0001\u0010!\u001a\u00020\"2\u000e\b\u0001\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0014\u00a2\u0006\u0002\u0010#J)\u0010 \u001a\u0002H\u001a\"\n\b\u0001\u0010\u001a*\u0004\u0018\u00010\u001b2\u000e\b\u0001\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001a0\u001dH\u0014\u00a2\u0006\u0002\u0010\u001eJ\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\'H&J\b\u0010(\u001a\u00020%H\u0016J\b\u0010)\u001a\u00020\nH\u0016J\b\u0010*\u001a\u00020\nH\u0016J\b\u0010+\u001a\u00020%H\u0016J\u0010\u0010,\u001a\u00020%2\u0006\u0010-\u001a\u00020.H\u0016J&\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u0001042\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u00105\u001a\u00020%H\u0016J\u0010\u00106\u001a\u00020%2\u0006\u00107\u001a\u00020\nH\u0016J\b\u00108\u001a\u00020%H\u0016J\u001a\u00109\u001a\u00020%2\u0006\u0010:\u001a\u0002002\b\u0010&\u001a\u0004\u0018\u00010\'H\u0016J\b\u0010;\u001a\u00020\nH\u0016J\b\u0010<\u001a\u00020%H\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u00028\u0000X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006="}, d2 = {"Lcom/lazylibs/component/ui/fragment/BaseVMFragment;", "DB", "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/Fragment;", "layoutId", "", "(I)V", "dialog", "Lcom/lazylibs/lifecycle/data/LoadingDialogFragment;", "isFirstLoadData", "", "getLayoutId", "()I", "mActivity", "Landroidx/appcompat/app/AppCompatActivity;", "mActivityProvider", "Landroidx/lifecycle/ViewModelProvider;", "mApplicationProvider", "mBinding", "getMBinding", "()Landroidx/databinding/ViewDataBinding;", "setMBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "mFragmentProvider", "getActivityScopeViewModel", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "getApplicationScopeViewModel", "getFragmentScopeViewModel", "owner", "Landroidx/lifecycle/ViewModelStoreOwner;", "(Landroidx/lifecycle/ViewModelStoreOwner;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "init", "", "savedInstanceState", "Landroid/os/Bundle;", "initData", "isInitialized", "isRegEventBus", "loadData", "onAttach", "context", "Landroid/content/Context;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "onHiddenChanged", "hidden", "onResume", "onViewCreated", "view", "onceLoadData", "startObserver", "lazylibs_debug"})
public abstract class BaseVMFragment<DB extends androidx.databinding.ViewDataBinding> extends androidx.fragment.app.Fragment {
    private final int layoutId = 0;
    private androidx.appcompat.app.AppCompatActivity mActivity;
    private androidx.lifecycle.ViewModelProvider mFragmentProvider;
    private androidx.lifecycle.ViewModelProvider mActivityProvider;
    private androidx.lifecycle.ViewModelProvider mApplicationProvider;
    private boolean isFirstLoadData = true;
    public DB mBinding;
    private com.lazylibs.lifecycle.data.LoadingDialogFragment dialog;
    
    public BaseVMFragment(@androidx.annotation.LayoutRes()
    int layoutId) {
        super();
    }
    
    public final int getLayoutId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final DB getMBinding() {
        return null;
    }
    
    public final void setMBinding(@org.jetbrains.annotations.NotNull()
    DB p0) {
    }
    
    @java.lang.Override()
    public void onAttach(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    public abstract void init(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState);
    
    protected <T extends androidx.lifecycle.ViewModel>T getFragmentScopeViewModel(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    protected <T extends androidx.lifecycle.ViewModel>T getFragmentScopeViewModel(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    androidx.lifecycle.ViewModelStoreOwner owner, @org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    protected <T extends androidx.lifecycle.ViewModel>T getActivityScopeViewModel(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    protected <T extends androidx.lifecycle.ViewModel>T getApplicationScopeViewModel(@org.jetbrains.annotations.NotNull()
    @androidx.annotation.NonNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    public void initData() {
    }
    
    public void startObserver() {
    }
    
    public boolean isRegEventBus() {
        return false;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    @java.lang.Override()
    public void onHiddenChanged(boolean hidden) {
    }
    
    public void loadData() {
    }
    
    /**
     * default true
     *
     * @return true:once loading false:loop loading
     */
    public boolean onceLoadData() {
        return false;
    }
    
    public boolean isInitialized() {
        return false;
    }
}