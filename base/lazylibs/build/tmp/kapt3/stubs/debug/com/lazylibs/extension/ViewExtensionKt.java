package com.lazylibs.extension;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 2, d1 = {"\u0000<\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0019\u0010\f\u001a\u00020\r\"\b\b\u0000\u0010\u0003*\u00020\u000e*\u0002H\u0003\u00a2\u0006\u0002\u0010\u000f\u001a-\u0010\u0010\u001a\u00020\u0011\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\u0002\u0010\u0014\u001a\u001b\u0010\u0015\u001a\u00020\u0016\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u0003H\u0002\u00a2\u0006\u0002\u0010\u0017\u001a7\u0010\u0018\u001a\u00020\u0011\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\u0002\u0010\u001a\u001a-\u0010\u001b\u001a\u00020\u0011\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\u0002\u0010\u0014\u001a7\u0010\u001c\u001a\u00020\u0011\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\b\b\u0002\u0010\u0019\u001a\u00020\u00012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\u0002\u0010\u001a\u001a#\u0010\u001d\u001a\u00020\u0011\"\b\b\u0000\u0010\u0003*\u00020\u001e*\u0002H\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010 \u001a#\u0010!\u001a\u00020\u0011\"\b\b\u0000\u0010\u0003*\u00020\u001e*\u0002H\u00032\b\u0010\"\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010 \u001a#\u0010#\u001a\u0002H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\b\b\u0002\u0010$\u001a\u00020\u0001\u00a2\u0006\u0002\u0010%\"2\u0010\u0002\u001a\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0006\u0010\u0000\u001a\u00020\u00018B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\"2\u0010\t\u001a\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u0002H\u00032\u0006\u0010\u0000\u001a\u00020\u00018B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006&"}, d2 = {"value", "", "triggerDelay", "T", "Landroid/view/View;", "getTriggerDelay", "(Landroid/view/View;)J", "setTriggerDelay", "(Landroid/view/View;J)V", "triggerLastTime", "getTriggerLastTime", "setTriggerLastTime", "autoSpilt", "", "Landroid/widget/TextView;", "(Landroid/widget/TextView;)Ljava/lang/String;", "click", "", "block", "Lkotlin/Function1;", "(Landroid/view/View;Lkotlin/jvm/functions/Function1;)V", "clickEnable", "", "(Landroid/view/View;)Z", "clickWithTrigger", "time", "(Landroid/view/View;JLkotlin/jvm/functions/Function1;)V", "longClick", "longClickWithTrigger", "setText", "Landroid/widget/EditText;", "text", "(Landroid/widget/EditText;Ljava/lang/String;)V", "setText2", "strText", "withTrigger", "delay", "(Landroid/view/View;J)Landroid/view/View;", "lazylibs_debug"})
public final class ViewExtensionKt {
    
    /**
     * *
     * 设置延迟时间的View扩展
     * @param delay Long 延迟时间，默认200毫秒
     * @return T
     */
    @org.jetbrains.annotations.NotNull()
    public static final <T extends android.view.View>T withTrigger(@org.jetbrains.annotations.NotNull()
    T $this$withTrigger, long delay) {
        return null;
    }
    
    /**
     * *
     * 点击事件的View扩展
     * @param block: (T) -> Unit 函数
     * @return Unit
     */
    public static final <T extends android.view.View>void click(@org.jetbrains.annotations.NotNull()
    T $this$click, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> block) {
    }
    
    public static final <T extends android.view.View>void longClick(@org.jetbrains.annotations.NotNull()
    T $this$longClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> block) {
    }
    
    public static final <T extends android.view.View>void longClickWithTrigger(@org.jetbrains.annotations.NotNull()
    T $this$longClickWithTrigger, long time, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> block) {
    }
    
    /**
     * *
     * 带延迟过滤的点击事件View扩展
     * @param delay Long 延迟时间，默认200毫秒
     * @param block: (T) -> Unit 函数
     * @return Unit
     */
    public static final <T extends android.view.View>void clickWithTrigger(@org.jetbrains.annotations.NotNull()
    T $this$clickWithTrigger, long time, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super T, kotlin.Unit> block) {
    }
    
    private static final <T extends android.view.View>long getTriggerLastTime(T $this$triggerLastTime) {
        return 0L;
    }
    
    private static final <T extends android.view.View>void setTriggerLastTime(T $this$triggerLastTime, long value) {
    }
    
    private static final <T extends android.view.View>long getTriggerDelay(T $this$triggerDelay) {
        return 0L;
    }
    
    private static final <T extends android.view.View>void setTriggerDelay(T $this$triggerDelay, long value) {
    }
    
    private static final <T extends android.view.View>boolean clickEnable(T $this$clickEnable) {
        return false;
    }
    
    public static final <T extends android.widget.EditText>void setText(@org.jetbrains.annotations.NotNull()
    T $this$setText, @org.jetbrains.annotations.Nullable()
    java.lang.String text) {
    }
    
    public static final <T extends android.widget.EditText>void setText2(@org.jetbrains.annotations.NotNull()
    T $this$setText2, @org.jetbrains.annotations.Nullable()
    java.lang.String strText) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final <T extends android.widget.TextView>java.lang.String autoSpilt(@org.jetbrains.annotations.NotNull()
    T $this$autoSpilt) {
        return null;
    }
}