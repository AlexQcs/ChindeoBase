package com.chindeo.repository.data.model.params.service;

import com.alibaba.fastjson.annotation.JSONField;

public class VoiceCallInfoParams {

    @JSONField(name = "callContent")
    public String callContent;

    @JSONField(name = "deviceCode")
    public String deviceCode;

    public VoiceCallInfoParams(String callContent, String deviceCode) {
        this.deviceCode = deviceCode;
        this.callContent = callContent;
    }

    public VoiceCallInfoParams() {
    }
}
