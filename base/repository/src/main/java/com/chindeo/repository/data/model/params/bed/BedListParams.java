package com.chindeo.repository.data.model.params.bed;

import com.alibaba.fastjson.annotation.JSONField;

public class BedListParams {

    @JSONField(name = "deviceId")
    public String deviceId;
    @JSONField(name = "eventId")
    public int eventId;

    public BedListParams(String deviceId, int eventId) {
        this.deviceId = deviceId;
        this.eventId = eventId;
    }

    public BedListParams() {
    }
}
