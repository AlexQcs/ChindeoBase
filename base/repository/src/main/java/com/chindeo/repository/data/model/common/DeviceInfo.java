package com.chindeo.repository.data.model.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.contants.DeviceType;

public class DeviceInfo {

    @JSONField(name = "id")
    public String id;
    @JSONField(name = "type")
    public DeviceType type;

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "id='" + id + '\'' +
                ", type=" + type +
                '}';
    }
}
