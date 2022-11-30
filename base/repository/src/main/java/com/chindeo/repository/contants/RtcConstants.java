package com.chindeo.repository.contants;

/**
 * 音视频挂断说明
 *
 */
public enum RtcConstants {
    //启动器
    HANG_UP_NORMALLY(0, "正常挂断"),
    OFFLINE(1, "号码不通"),
    BUSY(2, "正在通话中"),
    NO_ONE_HEARD(3, "无人接听"),
    REJECT(4, "对方拒接"),
    CANCEL(5, "对方取消"),
    DISCONNECT(6, "连接中断"),
    CONNECT_FAIL(7, "连接失败"),
    TYPE_CONFLICT_VIDEO(8, "对方正在视频通话，为您切换视频呼叫"),
    TYPE_CONFLICT_VOICE(9, "对方正在语音通话，为您切换语音呼叫"),
    ;

    public final int type;
    public final String desc;

    RtcConstants(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static RtcConstants getRtc(int type) {
        for (RtcConstants value : values()) {
            if (value.type == type) {
                return value;
            }
        }
        return null;
    }


}
