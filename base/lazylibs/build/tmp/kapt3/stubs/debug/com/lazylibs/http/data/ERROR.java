package com.lazylibs.http.data;

import java.lang.System;

/**
 * @auther : Aleyn
 * time   : 2019/08/12
 */
@kotlin.Metadata(mv = {1, 7, 1}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\u0003J\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000j\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/lazylibs/http/data/ERROR;", "", "code", "", "err", "", "(Ljava/lang/String;IILjava/lang/String;)V", "getKey", "getValue", "UNKNOWN", "PARSE_ERROR", "NETWORD_ERROR", "HTTP_ERROR", "SSL_ERROR", "TIMEOUT_ERROR", "lazylibs_debug"})
public enum ERROR {
    /*public static final*/ UNKNOWN /* = new UNKNOWN(0, null) */,
    /*public static final*/ PARSE_ERROR /* = new PARSE_ERROR(0, null) */,
    /*public static final*/ NETWORD_ERROR /* = new NETWORD_ERROR(0, null) */,
    /*public static final*/ HTTP_ERROR /* = new HTTP_ERROR(0, null) */,
    /*public static final*/ SSL_ERROR /* = new SSL_ERROR(0, null) */,
    /*public static final*/ TIMEOUT_ERROR /* = new TIMEOUT_ERROR(0, null) */;
    private final int code = 0;
    private final java.lang.String err = null;
    
    ERROR(int code, java.lang.String err) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getValue() {
        return null;
    }
    
    public final int getKey() {
        return 0;
    }
}