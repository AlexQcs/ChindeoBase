package com.chindeo.repository.data.model.response.watch;

import com.alibaba.fastjson.annotation.JSONField;

public class WatchDeviceInfo {


    @JSONField(name = "name")
    public String name;

    @JSONField(name = "userName")
    public String phoneNumber;

    @JSONField(name = "locName")
    public String locName;

    @JSONField(name = "locCode")
    public String locCode;

}
