package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

public class MissedRecordReturnBean {

    @JSONField(name = "id")
    public String id;
    @JSONField(name = "time")
    public long time;

    public MissedRecordReturnBean(String id) {
        this.id = id;
        time = System.currentTimeMillis();
    }

    public MissedRecordReturnBean() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissedRecordReturnBean that = (MissedRecordReturnBean) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
