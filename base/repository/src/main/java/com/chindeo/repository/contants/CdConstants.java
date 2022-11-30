package com.chindeo.repository.contants;

public interface CdConstants {

    interface Action {

        String SKIP_TO_CALL_ACTIVITY = "跳转callActivity";
        String SKIP_CLOSE_CALL_ACTIVITY = "关闭callActivity";
        String SKIP_SHOW_THREE_PARTY_CALL = "显示三方通话页面";

        //处理患者的电话
        String HANDLER_USER_PHONE = "webrtc_handler_user_phone";

        /**
         * 新版音视频是否显示界面
         */
        String PHONE_V3_ACTIVITY = "webrtc_phone_v3_activity";
        String LAUNCHER_RESTART = "重启启动器";

        String BED_COMMAND_ACTION = "床旁指令action";

        String NURSES_TIMING_ACTION = "护士站发送广播";

    }


    interface Params {

        /**
         * CategoryAckBean
         */
        String KEY_CALL_ACK_BEAN = "callAckBean";

        String KEY_CALL_IS_CALL_OUT = "呼出方";

        String KEY_CALL_PHONE_NUMBER = "对方号码";
        String KEY_CALL_HANDLER_TYPE_IS_HANGUP = "处理是否挂断";
        String KEY_CALL_END_EVENT_TAG = "通话结束类型";
        String KEY_CALL_TYPE = "呼叫的类型";
        String KEY_CALL_NUMBER = "拨打的号码";
        String KEY_CALL_NAME = "拨打的床号";
        String KEY_CALL_END_REASON = "挂断的原因";
        String KEY_BED_COMMAND_KEY = "床旁指令key";
    }

}
