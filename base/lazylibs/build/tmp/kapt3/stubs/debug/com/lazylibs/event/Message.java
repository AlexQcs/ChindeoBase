package com.lazylibs.event;

import java.lang.System;

/**
 * @auther : Aleyn
 * time   : 2019/11/13
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\u0018\u00002\u00020\u0001B;\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0001\u00a2\u0006\u0002\u0010\tR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/lazylibs/event/Message;", "", "code", "", "msg", "", "arg1", "arg2", "obj", "(ILjava/lang/String;IILjava/lang/Object;)V", "getArg1", "()I", "setArg1", "(I)V", "getArg2", "setArg2", "getCode", "setCode", "getMsg", "()Ljava/lang/String;", "setMsg", "(Ljava/lang/String;)V", "getObj", "()Ljava/lang/Object;", "setObj", "(Ljava/lang/Object;)V", "lazylibs_debug"})
public final class Message {
    private int code;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String msg;
    private int arg1;
    private int arg2;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Object obj;
    
    @kotlin.jvm.JvmOverloads()
    public Message() {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public Message(int code) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public Message(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public Message(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg, int arg1) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public Message(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg, int arg1, int arg2) {
        super();
    }
    
    @kotlin.jvm.JvmOverloads()
    public Message(int code, @org.jetbrains.annotations.NotNull()
    java.lang.String msg, int arg1, int arg2, @org.jetbrains.annotations.Nullable()
    java.lang.Object obj) {
        super();
    }
    
    public final int getCode() {
        return 0;
    }
    
    public final void setCode(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMsg() {
        return null;
    }
    
    public final void setMsg(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getArg1() {
        return 0;
    }
    
    public final void setArg1(int p0) {
    }
    
    public final int getArg2() {
        return 0;
    }
    
    public final void setArg2(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getObj() {
        return null;
    }
    
    public final void setObj(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
    }
}