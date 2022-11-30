package com.chindeo.repository.data.model.params.bed;

import com.alibaba.fastjson.annotation.JSONField;

public class EducationListParams {

    @JSONField(name = "type") //宣教类型|类别,当是文件分类（0:视频,1:音乐,2:ppt），否则逻辑分类（0：普通宣教，1：健康宣教）
    public String type;
    @JSONField(name = "locCode")
    public String locCode;
    @JSONField(name = "inFile")
    public int inFile;
    @JSONField(name = "device") //0：大屏，1床头屏
    public int deviceType = 1;
    @JSONField(name = "itemType") //1：入院宣教，2：出院宣教，3：术前宣教，4：术后宣教，5：医嘱宣教，6：诊断宣教，7：过敏原宣教，8：慢病预防宣教，9：专科护理，10：基础护理，11：培训计划，12：健康饮食
    public String itemType;
    @JSONField(name = "admNo")
    public String admNo;

    public EducationListParams(String type, String locCode, boolean isFile) {
        this.type = type;
        this.locCode = locCode;
        inFile = isFile ? 1: 0;
    }

    public EducationListParams(String type, String locCode, boolean isFile, String admNo) {
        this.type = type;
        this.locCode = locCode;
        inFile = isFile ? 1: 0;
        this.admNo = admNo;
    }
}
