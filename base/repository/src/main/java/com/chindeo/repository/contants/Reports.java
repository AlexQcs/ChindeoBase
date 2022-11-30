package com.chindeo.repository.contants;

public enum Reports {

    USER_INFO("患者信息"),
    MEDICAL_LOGIN("医护人员登录"),
    VIDEO_CALL("视频呼叫"),
    VOICE_CALL("语音呼叫"),
    MESSAGE_CALL("信息呼叫"),
    MEDICAL_EVALUATION("医护评价"),
    TREATMENT_PLAN("诊疗计划"),
    QUICK_QUERY("快速查询"),
    HEALTH_EDUCATION("健康宣教"),
    INTELLIGENT_REMINDER("智能提醒"),
    SMART_TV("智能电视"),
    VITAL_SIGNS("患者-生命体征"),
    NURSE_VITAL_SIGNS("护士-生命体征"),
    DOCTOR_VITAL_SIGNS("医生-生命体征"),
    SATISFACTION_EVALUATION("满意评价"),
    HOSPITAL_INTRODUCTION("医院介绍"),
    SPECIALIST("专科专家"),
    MEDICAL_GUIDE("就医指南"),
    SPECIAL_MATTERS("特别事项"),
    TUBE_BED_SETTING("管床设置"),
    BEDSIDE_ROUNDS("床旁巡视"),
    RISK_ASSESSMENT("风险评估"),
    ORDER_EXECUTION("医嘱执行"),
    SET_REMINDER("设置提醒"),
    JOIN_NURSING("进入护理"),
    ;

    public final String name;

    Reports(String name) {
        this.name = name;
    }
}
