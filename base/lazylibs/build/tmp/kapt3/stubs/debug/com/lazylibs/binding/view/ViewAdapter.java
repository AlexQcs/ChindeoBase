package com.lazylibs.binding.view;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J(\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\bH\u0007J*\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0001H\u0007J\"\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u000bH\u0007J \u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\n\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000bH\u0007J*\u0010\u000f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\u0006\u0010\r\u001a\u00020\u0001H\u0007J\"\u0010\u0010\u001a\u00020\u00042\b\u0010\u0011\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u000bH\u0007J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\bH\u0007\u00a8\u0006\u0015"}, d2 = {"Lcom/lazylibs/binding/view/ViewAdapter;", "", "()V", "isVisible", "", "view", "Landroid/view/View;", "visibility", "", "onClickCommand", "clickCommand", "Lcom/lazylibs/binding/command/BindingCommand;", "isThrottleFirst", "item", "onFocusChangeCommand", "onLongClickCommand", "replyCurrentView", "currentView", "bindingCommand", "requestFocusCommand", "needRequestFocus", "lazylibs_debug"})
public final class ViewAdapter {
    @org.jetbrains.annotations.NotNull()
    public static final com.lazylibs.binding.view.ViewAdapter INSTANCE = null;
    
    private ViewAdapter() {
        super();
    }
    
    /**
     * requireAll 是意思是是否需要绑定全部参数, false为否
     * View的onClick事件绑定
     * onClickCommand 绑定的命令,
     * isThrottleFirst 是否开启防止过快点击
     */
    @androidx.databinding.BindingAdapter(value = {"onClickCommand", "isThrottleFirst"}, requireAll = false)
    @kotlin.jvm.JvmStatic()
    public static final void onClickCommand(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    com.lazylibs.binding.command.BindingCommand<?> clickCommand, boolean isThrottleFirst) {
    }
    
    /**
     * 列表Item点击事件并携带item的数据
     *
     * @param view 点击事件相应的view
     * @param clickCommand 发起点击事件者
     * @param item 业务每个Item数据
     */
    @androidx.databinding.BindingAdapter(value = {"onRvItemCommand", "rvItemBean"})
    @kotlin.jvm.JvmStatic()
    public static final void onClickCommand(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    com.lazylibs.binding.command.BindingCommand<java.lang.Object> clickCommand, @org.jetbrains.annotations.NotNull()
    java.lang.Object item) {
    }
    
    /**
     * 列表Item点击事件并携带item的数据
     *
     * @param view 点击事件相应的view
     * @param clickCommand 发起点击事件者
     * @param item 业务每个Item数据
     */
    @androidx.databinding.BindingAdapter(value = {"onRvLongItemCommand", "rvItemBean"})
    @kotlin.jvm.JvmStatic()
    public static final void onLongClickCommand(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    com.lazylibs.binding.command.BindingCommand<java.lang.Object> clickCommand, @org.jetbrains.annotations.NotNull()
    java.lang.Object item) {
    }
    
    /**
     * view的onLongClick事件绑定
     */
    @androidx.databinding.BindingAdapter(value = {"onLongClickCommand"}, requireAll = false)
    @kotlin.jvm.JvmStatic()
    public static final void onLongClickCommand(@org.jetbrains.annotations.Nullable()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    com.lazylibs.binding.command.BindingCommand<?> clickCommand) {
    }
    
    /**
     * 回调控件本身
     *
     * @param currentView
     * @param bindingCommand
     */
    @androidx.databinding.BindingAdapter(value = {"currentView"}, requireAll = false)
    @kotlin.jvm.JvmStatic()
    public static final void replyCurrentView(@org.jetbrains.annotations.Nullable()
    android.view.View currentView, @org.jetbrains.annotations.Nullable()
    com.lazylibs.binding.command.BindingCommand<android.view.View> bindingCommand) {
    }
    
    /**
     * view是否需要获取焦点
     */
    @androidx.databinding.BindingAdapter(value = {"requestFocus"})
    @kotlin.jvm.JvmStatic()
    public static final void requestFocusCommand(@org.jetbrains.annotations.NotNull()
    android.view.View view, boolean needRequestFocus) {
    }
    
    /**
     * view的焦点发生变化的事件绑定
     */
    @androidx.databinding.BindingAdapter(value = {"onFocusChangeCommand"})
    @kotlin.jvm.JvmStatic()
    public static final void onFocusChangeCommand(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    com.lazylibs.binding.command.BindingCommand<java.lang.Boolean> onFocusChangeCommand) {
    }
    
    /**
     * view的显示隐藏 true 显示 false 隐藏
     */
    @androidx.databinding.BindingAdapter(value = {"isVisible"}, requireAll = false)
    @kotlin.jvm.JvmStatic()
    public static final void isVisible(@org.jetbrains.annotations.NotNull()
    android.view.View view, boolean visibility) {
    }
}