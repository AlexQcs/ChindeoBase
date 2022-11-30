package com.chindeo.repository.data.model.response.call;

import com.alibaba.fastjson.annotation.JSONField;

public class AudioBroadcastResourceBean {

    @JSONField(name = "name")
    public String name;
    @JSONField(name = "url")
    public String url; // mp4
}
