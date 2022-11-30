package com.chindeo.repository.data.model.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.contants.HttpResultCode;
import com.lazylibs.http.data.IBaseResponse;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Created by leo
 * on 17/12/26.
 * 这个类是泛型类，可根据后端的返回字段修改
 */
public class HttpResult<T> implements IBaseResponse<T>,Serializable {
    public T data;
    @JSONField(alternateNames = {"code", "status"})
    public int code;
    @JSONField(name = "message")
    public String errorMsg;

    public boolean isSuccess(){
        return HttpResultCode.SUCCESS == code;
    }

    @Override
    public int code() {
        return code;
    }

    @Nullable
    @Override
    public String msg() {
        return errorMsg;
    }

    @Override
    public T data() {
        return data;
    }
}
