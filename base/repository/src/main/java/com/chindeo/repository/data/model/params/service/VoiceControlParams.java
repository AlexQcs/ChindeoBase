package com.chindeo.repository.data.model.params.service;

import com.alibaba.fastjson.annotation.JSONField;

public class VoiceControlParams {

    @JSONField(name = "simulation")
    public String simulation;
    @JSONField(name = "command")
    public String command;
    @JSONField(name = "mode")
    public int mode = 0; // 0大屏控制模式 1大屏输入模式

}
