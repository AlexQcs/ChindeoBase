package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

public class BedPlanBean {

    @JSONField(name = "name")
    public String name;

    public BedPlanBean(String name) {
        this.name = name;
    }

    public BedPlanBean() {
    }
}
