package com.chindeo.repository.data.model.response.bed;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author chindeo_zcg
 * @Date 2022/7/8-9:49
 * @Email chrisSpringSmell@gmail.com
 */
public class BedVolumeDetailBean {


    @JSONField(name = "deviceId")
    public int deviceId;
    @JSONField(name = "deviceName")
    public String deviceName;
    @JSONField(name = "deviceType")
    public String deviceType;
    @JSONField(name = "volume1")
    public int volume1;
    @JSONField(name = "startTime1")
    public String startTime1;
    @JSONField(name = "endTime1")
    public String endTime1;
    @JSONField(name = "volume2")
    public int volume2;
    @JSONField(name = "startTime2")
    public String startTime2;
    @JSONField(name = "endTime2")
    public String endTime2;

    @JSONField(name = "code")
    public String code;

    public Boolean isCheck = false;

    public BedVolumeDetailBean() {
    }

    public BedVolumeDetailBean(int deviceId, String deviceName, String deviceType, int volume1, int volume2, String code) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.volume1 = volume1;
        this.volume2 = volume2;
        this.code = code;
    }

    @Override
    public String toString() {
        return "BedVolumeDetailBean{" +
                "deviceId=" + deviceId +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", volume1=" + volume1 +
                ", startTime1='" + startTime1 + '\'' +
                ", endTime1='" + endTime1 + '\'' +
                ", volume2=" + volume2 +
                ", startTime2='" + startTime2 + '\'' +
                ", endTime2='" + endTime2 + '\'' +
                ", code='" + code + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
