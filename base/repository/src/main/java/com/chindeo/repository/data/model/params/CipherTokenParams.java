package com.chindeo.repository.data.model.params;

import com.alibaba.fastjson.annotation.JSONField;

public class CipherTokenParams {

    @JSONField(name = "mac")
    public String mac;
    @JSONField(name = "cipher")
    public String licenseToken;

    public CipherTokenParams(String mac, String licenseToken) {
        this.mac = mac;
        this.licenseToken = licenseToken;
    }
}
