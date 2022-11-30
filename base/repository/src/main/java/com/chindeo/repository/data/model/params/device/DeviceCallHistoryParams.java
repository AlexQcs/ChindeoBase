package com.chindeo.repository.data.model.params.device;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceCallHistoryParams {

    @JSONField(name = "deviceId")
    public String deviceId;
    @JSONField(name = "endDate")
    public String endDate;
    @JSONField(name = "startDate")
    public String startDate;
    @JSONField(name = "type")
    public String type;

}
