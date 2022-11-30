package com.chindeo.repository.contants;

public class MqttConstants {
    public static final String SUBSCRIBE_LINE = "/";
    public static final String SUBSCRIBE_PREFIX = "app";
    public static final String SUBSCRIBE_SOURCE_WHITEBOARD = "nis";  //护理交互系统  Nursing interaction system
    public static final String SUBSCRIBE_SOURCE_BED = "bis";         //床头交互系统  Bedside interactive system
    public static final String SUBSCRIBE_SOURCE_STATION = "nws";     //护理工作站    Nursing workstation system
    public static final String SUBSCRIBE_SOURCE_WEB_APP = "webapp";  //前端产品
    public static final String SUBSCRIBE_SOURCE_WATCH = "watch";     //手表
    public static final String SUBSCRIBE_ROLE_DOCTOR = "doctor";
    public static final String SUBSCRIBE_ROLE_NURSE = "nurse";
    public static final String SUBSCRIBE_ROLE_PATIENT = "patient";

    //聊天
    public static final String SUBSCRIBE_IMS = "ims";
    public static final String SUBSCRIBE_GROUP = "group";
    public static final String SUBSCRIBE_USER = "user";
    //协议方式
    public static final String SUBSCRIBE_TCP = "tcp";
    public static final String SUBSCRIBE_WS = "ws";
    public static final String SUBSCRIBE_WCS = "wcs";


    public static final String SUBSCRIBE_IMS_COMMON = "app/ims/group/";

    public static final String MQTT_TOPIC="topic";
    public static final String MQTT_MESSAGE="message";

    public static final String CATEGORY_CALL_END = "callEnd";

    public static final String BED_APP_REFRESH_CARE_SETTINGS="更新护理通知";
}
