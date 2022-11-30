package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageUnReadBean {

    @JSONField(name = "admNo")
    public String admNo;
    @JSONField(name = "ids")
    public List<String> msgIds;

    public MessageUnReadBean() {
    }

    public MessageUnReadBean(String admNo) {
        this.admNo = admNo;
        msgIds = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageUnReadBean that = (MessageUnReadBean) o;
        return Objects.equals(admNo, that.admNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admNo);
    }
}
