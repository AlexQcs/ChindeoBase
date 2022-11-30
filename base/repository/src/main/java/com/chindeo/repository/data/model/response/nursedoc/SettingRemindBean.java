package com.chindeo.repository.data.model.response.nursedoc;

import com.alibaba.fastjson.annotation.JSONField;
//消息提醒bean
public class SettingRemindBean {

    @JSONField(name = "id")
    public int id;
    @JSONField(name = "admId")
    public int admId;
    @JSONField(name = "title")
    public String title;
    @JSONField(name = "type")
    public String type;
    @JSONField(name = "remindDate")
    public String remindDate;
    @JSONField(name = "repeatFreq")
    public int repeatFreq;
    @JSONField(name = "createDate")
    public String createDate;
}
