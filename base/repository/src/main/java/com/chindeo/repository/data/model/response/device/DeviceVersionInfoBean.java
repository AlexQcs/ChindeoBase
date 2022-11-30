package com.chindeo.repository.data.model.response.device;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceVersionInfoBean {


    @JSONField(name = "createTime")
    public String createTime;
    @JSONField(name = "id")
    public int id;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "remarks")
    public String remarks;
    @JSONField(name = "size")
    public int size;
    @JSONField(name = "type")
    public String type;
    @JSONField(name = "userId")
    public int userId;
    @JSONField(name = "version")
    public String version;
}
