package com.chindeo.repository.data.model.response.upgrade;

import com.alibaba.fastjson.annotation.JSONField;

public class PrimaryAppBean {
//    {
//        "name": "com.chindeo.bed.app",
//            "type": "2"
//    }

    @JSONField(name = "name")
    public String name;
    @JSONField(name = "type")
    public String type;
    @JSONField(name = "env")
    public String env;

}
