package com.chindeo.repository.data.model.setting;

public enum MQTTStatusEnum {

    WAITING("待连接", "0"), //未启动或未连接,无响应状态
    ONLINE("已连接", "1"),
    OFFLINE("离线", "2"),
    UN_KNOW("未知", "-99")
    ;

    public final String desc;
    public final String statusCode;

    MQTTStatusEnum(String desc, String statusCode) {
        this.desc = desc;
        this.statusCode = statusCode;
    }

    public static MQTTStatusEnum getStatus(String statusCode){
        for (MQTTStatusEnum value : values()) {
            if (value.statusCode.equals(statusCode)){
                return value;
            }
        }
        return UN_KNOW;
    }

}
