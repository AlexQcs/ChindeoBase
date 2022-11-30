package com.lazylibs.http.data;

import java.lang.System;

/**
 * @auther : Aleyn
 * time   : 2020/01/13
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\r\u0010\u0005\u001a\u00028\u0000H&\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH&J\n\u0010\t\u001a\u0004\u0018\u00010\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/lazylibs/http/data/IBaseResponse;", "T", "", "code", "", "data", "()Ljava/lang/Object;", "isSuccess", "", "msg", "", "lazylibs_debug"})
public abstract interface IBaseResponse<T extends java.lang.Object> {
    
    public abstract int code();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.String msg();
    
    public abstract T data();
    
    public abstract boolean isSuccess();
}