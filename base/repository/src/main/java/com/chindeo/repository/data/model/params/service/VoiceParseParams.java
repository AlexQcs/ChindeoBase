package com.chindeo.repository.data.model.params.service;

import com.alibaba.fastjson.annotation.JSONField;

public class VoiceParseParams {
    @JSONField(name = "data")
    public String data;

    public VoiceParseParams(String data) {
        this.data = data;
    }

}
