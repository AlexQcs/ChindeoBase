package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 屏蔽对象类
 */
public class PatientAuthorityBean {

    @JSONField(name = "exam")
    public Boolean exam; // 屏蔽检验结果
    @JSONField(name = "inspect")
    public Boolean inspect; // 屏蔽检查结果
    @JSONField(name = "diagnosis")
    public Boolean diagnosis; // 屏蔽诊断结果

}
