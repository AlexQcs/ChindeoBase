package com.chindeo.repository.data.model.params.call;

import com.alibaba.fastjson.annotation.JSONField;
import com.lazylibs.util.TimeUtils;

public class ChatHistoryParams {

    @JSONField(name = "admNo")
    public String admNo; // 就诊号
    @JSONField(name = "date")
    public String date;  // 查询起始时间

    public ChatHistoryParams(String admNo, String date) {
        this.admNo = admNo;
        this.date = date;
    }
}
