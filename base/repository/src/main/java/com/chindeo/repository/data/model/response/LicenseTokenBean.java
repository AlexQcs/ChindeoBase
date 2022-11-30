package com.chindeo.repository.data.model.response;

import com.alibaba.fastjson.annotation.JSONField;

public class LicenseTokenBean {


    @JSONField(name = "access_token")
    public String token;   //token序列号
    @JSONField(name = "expire_in")
    public String expire_in;      //到期时间


}
