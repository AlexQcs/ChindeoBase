package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

public class LocBean {

    @JSONField(name = "patientNum")
    public int patientNum;
    @JSONField(name = "code")
    public String code;
    @JSONField(name = "id")
    public int id;
    @JSONField(name = "bedNum")
    public int bedNum;
    @JSONField(name = "desc")
    public String desc;

}
