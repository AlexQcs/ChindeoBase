package com.chindeo.repository.data.model.params.bed;

import com.alibaba.fastjson.annotation.JSONField;

public class RemindReadParams {

    @JSONField(name = "id")
    public String msgNo; //38115ce8-5337-439a-a553-5604e68ef07c
    @JSONField(name = "type")
    public String type;
    @JSONField(name = "mode")
    public int mode;

    public RemindReadParams(String msgNo) {
        this.msgNo = msgNo;
        type = "crash";
        mode = 0;
    }
}
