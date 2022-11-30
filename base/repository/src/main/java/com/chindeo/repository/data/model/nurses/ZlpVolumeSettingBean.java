package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

public class ZlpVolumeSettingBean {

    @JSONField(name = "deviceCode")
    public String deviceCode;

    @JSONField(name = "volume")
    public int volume;

    public ZlpVolumeSettingBean(String deviceCode, int volume) {
        this.deviceCode = deviceCode;
        this.volume = volume;
    }

    public ZlpVolumeSettingBean() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZlpVolumeSettingBean that = (ZlpVolumeSettingBean) o;
        return Objects.equals(deviceCode, that.deviceCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceCode);
    }
}
