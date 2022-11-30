package com.chindeo.repository.data.model.response;

import com.alibaba.fastjson.annotation.JSONField;

public class CipherTokenBean {
    @JSONField(name = "isExp")
    public boolean isExp;//token是否过期":false,
    @JSONField(name = "token")
    public String token;
}
