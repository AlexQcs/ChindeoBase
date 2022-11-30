package com.lazylibs.component.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0092\u0001\u0010\f\u001a\u00020\r2\'\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000f\u00a2\u0006\u0002\b\u00132-\u0010\u0014\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015\u00a2\u0006\u0002\b\u00132\'\u0010\u0017\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000f\u00a2\u0006\u0002\b\u0013H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J8\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001a\"\u0004\b\u0000\u0010\u001b2\u001c\u0010\u000e\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u001c\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u009d\u0001\u0010\u001e\u001a\u00020\r2\'\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000f\u00a2\u0006\u0002\b\u00132/\b\u0002\u0010\u0014\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0015\u00a2\u0006\u0002\b\u00132)\b\u0002\u0010\u0017\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000f\u00a2\u0006\u0002\b\u00132\b\b\u0002\u0010\u001f\u001a\u00020 \u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J\u0087\u0001\u0010\"\u001a\u00020\r\"\u0004\b\u0000\u0010\u001b2-\u0010\u000e\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0#0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000f\u00a2\u0006\u0002\b\u00132\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u00020\r0\u001c2\u0014\b\u0002\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\r0\u001c2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0%2\b\b\u0002\u0010\u001f\u001a\u00020 \u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J7\u0010\'\u001a\u00020(2\'\u0010\u000e\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000f\u00a2\u0006\u0002\b\u0013\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lcom/lazylibs/component/ui/BaseViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "Landroidx/lifecycle/LifecycleObserver;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "loadingContainer", "Lcom/lazylibs/lifecycle/data/ILoadingState;", "getLoadingContainer", "()Lcom/lazylibs/lifecycle/data/ILoadingState;", "loadingContainer$delegate", "Lkotlin/Lazy;", "handleException", "", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "error", "Lkotlin/Function3;", "Lcom/lazylibs/http/data/ResponseThrowable;", "complete", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/flow/Flow;", "launchGo", "isShowDialog", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Z)V", "launchOnlyResult", "Lcom/lazylibs/http/data/IBaseResponse;", "success", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Z)V", "launchUI", "Lkotlinx/coroutines/Job;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "lazylibs_debug"})
public class BaseViewModel extends androidx.lifecycle.AndroidViewModel implements androidx.lifecycle.LifecycleObserver {
    private final kotlin.Lazy loadingContainer$delegate = null;
    
    public BaseViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    private final com.lazylibs.lifecycle.data.ILoadingState getLoadingContainer() {
        return null;
    }
    
    /**
     * 所有网络请求都在 viewModelScope 域中启动，当页面销毁时会自动
     * 调用ViewModel的  #onCleared 方法取消所有协程
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job launchUI(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super kotlinx.coroutines.CoroutineScope, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block) {
        return null;
    }
    
    /**
     * 用流的方式进行网络请求
     */
    @org.jetbrains.annotations.NotNull()
    public final <T extends java.lang.Object>kotlinx.coroutines.flow.Flow<T> launchFlow(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> block) {
        return null;
    }
    
    /**
     * 不过滤请求结果
     * @param block 请求体
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    public final void launchGo(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super kotlinx.coroutines.CoroutineScope, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super kotlinx.coroutines.CoroutineScope, ? super com.lazylibs.http.data.ResponseThrowable, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> error, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super kotlinx.coroutines.CoroutineScope, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> complete, boolean isShowDialog) {
    }
    
    /**
     * 过滤请求结果，其他全抛异常
     * @param block 请求体
     * @param success 成功回调
     * @param error 失败回调
     * @param complete  完成回调（无论成功失败都会调用）
     * @param isShowDialog 是否显示加载框
     */
    public final <T extends java.lang.Object>void launchOnlyResult(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super kotlinx.coroutines.CoroutineScope, ? super kotlin.coroutines.Continuation<? super com.lazylibs.http.data.IBaseResponse<T>>, ? extends java.lang.Object> block, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> success, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.lazylibs.http.data.ResponseThrowable, kotlin.Unit> error, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> complete, boolean isShowDialog) {
    }
    
    /**
     * 异常统一处理
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object handleException(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super kotlinx.coroutines.CoroutineScope, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> block, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function3<? super kotlinx.coroutines.CoroutineScope, ? super com.lazylibs.http.data.ResponseThrowable, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> error, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super kotlinx.coroutines.CoroutineScope, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> complete, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}