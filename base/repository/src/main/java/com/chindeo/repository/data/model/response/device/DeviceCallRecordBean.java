package com.chindeo.repository.data.model.response.device;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceCallRecordBean {


    @JSONField(name = "destName")
    public String destName;
    @JSONField(name = "srcName")
    public String srcName;
    @JSONField(name = "bedCode")
    public String bedCode;
    @JSONField(name = "admName")
    public String admName;
    @JSONField(name = "srcNumber")
    public String srcNumber;
    @JSONField(name = "startTime")
    public String startTime;
    @JSONField(name = "endTime")
    public String endTime;
    @JSONField(name = "destNumber")
    public String destNumber;
    @JSONField(name = "callType")
    public int callType; // 1呼出 0呼入
    @JSONField(name = "hangupCause")
    public String hangupCause;
}
