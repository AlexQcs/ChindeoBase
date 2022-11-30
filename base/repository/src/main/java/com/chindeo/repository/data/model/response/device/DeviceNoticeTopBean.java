package com.chindeo.repository.data.model.response.device;

import com.alibaba.fastjson.annotation.JSONField;

public class DeviceNoticeTopBean {

    @JSONField(name = "read")
    public String read;
    @JSONField(name = "createTime")
    public String createTime;
    @JSONField(name = "sendType")
    public String sendType;
    @JSONField(name = "startTime")
    public long startTime;
    @JSONField(name = "id")
    public String id;
    @JSONField(name = "endTime")
    public long endTime;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "type")
    public String type;
    @JSONField(name = "content")
    public String content="";
}
