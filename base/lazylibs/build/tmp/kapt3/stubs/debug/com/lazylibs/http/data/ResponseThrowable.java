package com.lazylibs.http.data;

import java.lang.System;

@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001b\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007B%\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\fB\u001f\b\u0016\u0012\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u000fR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/lazylibs/http/data/ResponseThrowable;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "error", "Lcom/lazylibs/http/data/ERROR;", "e", "", "(Lcom/lazylibs/http/data/ERROR;Ljava/lang/Throwable;)V", "code", "", "msg", "", "(ILjava/lang/String;Ljava/lang/Throwable;)V", "base", "Lcom/lazylibs/http/data/IBaseResponse;", "(Lcom/lazylibs/http/data/IBaseResponse;Ljava/lang/Throwable;)V", "getCode", "()I", "setCode", "(I)V", "errMsg", "getErrMsg", "()Ljava/lang/String;", "setErrMsg", "(Ljava/lang/String;)V", "lazylibs_debug"})
public class ResponseThrowable extends java.lang.Exception {
    private int code;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String errMsg;
    
    public final int getCode() {
        return 0;
    }
    
    public final void setCode(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getErrMsg() {
        return null;
    }
    
    public final void setErrMsg(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    public ResponseThrowable(@org.jetbrains.annotations.NotNull()
    com.lazylibs.http.data.ERROR error, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable e) {
        super();
    }
    
    public ResponseThrowable(int code, @org.jetbrains.annotations.Nullable()
    java.lang.String msg, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable e) {
        super();
    }
    
    public ResponseThrowable(@org.jetbrains.annotations.NotNull()
    com.lazylibs.http.data.IBaseResponse<?> base, @org.jetbrains.annotations.Nullable()
    java.lang.Throwable e) {
        super();
    }
}