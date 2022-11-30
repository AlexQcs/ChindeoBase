package com.chindeo.repository.data.model.response.bed;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author chindeo_zcg
 * @Date 2022/7/8-9:49
 * @Email chrisSpringSmell@gmail.com
 */
public class BedVolumeSetBean {


    @JSONField(name = "deviceId")
    public int deviceId;
    @JSONField(name = "id")
    public String id;
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

    public BedVolumeSetBean() {
    }

    public BedVolumeSetBean(int deviceId, String id, int volume1, String startTime1, String endTime1) {
        this.deviceId = deviceId;
        this.id = id;
        this.volume1 = volume1;
        this.startTime1 = startTime1;
        this.endTime1 = endTime1;
    }

    public BedVolumeSetBean(int deviceId, String id, int volume1, String startTime1, String endTime1, int volume2, String startTime2, String endTime2) {
        this.deviceId = deviceId;
        this.id = id;
        this.volume1 = volume1;
        this.startTime1 = startTime1;
        this.endTime1 = endTime1;
        this.volume2 = volume2;
        this.startTime2 = startTime2;
        this.endTime2 = endTime2;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "deviceId=" + deviceId +
//                ", id='" + id + '\'' +
//                ", volume1=" + volume1 +
//                ", startTime1='" + startTime1 + '\'' +
//                ", endTime1='" + endTime1 + '\'' +
//                ", volume2=" + volume2 +
//                ", startTime2='" + startTime2 + '\'' +
//                ", endTime2='" + endTime2 + '\'' +
//                '}';
//    }
}
