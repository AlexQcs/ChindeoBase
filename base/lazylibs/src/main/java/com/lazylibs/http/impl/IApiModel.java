package com.lazylibs.http.impl;

public interface IApiModel {

    void onSuccess(ApiModel apiModel, Object data);

    void onError(ApiModel apiModel, Throwable e);

}