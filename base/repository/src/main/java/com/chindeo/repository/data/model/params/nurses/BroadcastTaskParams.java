package com.chindeo.repository.data.model.params.nurses;

import com.alibaba.fastjson.annotation.JSONField;

public class BroadcastTaskParams {
    @JSONField(name = "locCode")
    public String locCode;
    @JSONField(name = "msg")
    public String msg;
    @JSONField(name = "remindTime")
    public String remindTime;
    @JSONField(name = "repeat")
    public String repeat; // 重复周期 0周一至周五 1周末 2每天 3不重复
    @JSONField(name = "topic")
    public String topic;

    public BroadcastTaskParams(String locCode, String msg, String remindTime, String topic, String timeRepeatCode) {
        this.locCode = locCode;
        this.msg = msg;
        this.remindTime = remindTime;
        this.topic = topic;
        this.repeat = timeRepeatCode;
    }
}

