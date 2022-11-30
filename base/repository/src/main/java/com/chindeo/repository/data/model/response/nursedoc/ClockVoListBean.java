package com.chindeo.repository.data.model.response.nursedoc;

import com.alibaba.fastjson.annotation.JSONField;

//打卡记录bean
public class ClockVoListBean {

    @JSONField(name = "ci_id")
    public int ciId;
    @JSONField(name = "realTime")
    public String realTime;
    @JSONField(name = "ci_start_time")
    public String ciStartTime;
    @JSONField(name = "pa_adm_id")
    public int paAdmId;
    @JSONField(name = "ci_type")
    public String ciType;
    @JSONField(name = "ci_end_time")
    public String ciEndTime;
    @JSONField(name = "dateRange")
    public String dateRange;
    @JSONField(name = "is_delete")
    public String isDelete;
}
