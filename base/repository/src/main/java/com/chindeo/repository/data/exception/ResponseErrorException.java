package com.chindeo.repository.data.exception;

import androidx.annotation.RestrictTo;

public class ResponseErrorException extends Exception {

    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    protected final String errorMessage;

    @RestrictTo(RestrictTo.Scope.SUBCLASSES)
    protected final int code;

    public ResponseErrorException(int code, String errorMessage) {
        super("ResponseErrorException : " +
                "the error state is " + code + " , " +
                "the error error is " + errorMessage);
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public int state() {
        return code;
    }

    public String error() {
        return errorMessage;
    }

}
