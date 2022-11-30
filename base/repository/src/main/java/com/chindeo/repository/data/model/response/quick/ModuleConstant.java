package com.chindeo.repository.data.model.response.quick;

/**
 * 模块通信的常量定义  对应 ModuleEvent 的 id
 */
public interface ModuleConstant {

    int SICK_GO_DOCTOR = 2;         //患者去医生主界面
    int SICK_GO_NURSE = 3;          //患者去护士主界面
    int LOGIN_SUCCESS = 4;          //医生、护士，登录成功
    int NOTICE_DETAIL = 5;          //查看消息详情
    int RESTART = 6;                //重启
    int BACK_ENTRANCE = 7;          //子功能返回概念版
    int CALL_VIDEO = 8;          //依赖模块呼叫子模块 视频
    int CALL_VOICE = 9;          //依赖模块呼叫子模块 音频
    int LOGIN_NFC = 10;          //nfcLogin

}
