package com.chindeo.repository.data.model.params.audio;

import com.alibaba.fastjson.annotation.JSONField;

public class AudioRecordListParams {
    @JSONField(name = "flag")
    public int flag;
    @JSONField(name = "locCode")
    public String locCode   ;
    @JSONField(name = "endDate")
    public String endDate;
    @JSONField(name = "startDate")
    public String startDate;
}
