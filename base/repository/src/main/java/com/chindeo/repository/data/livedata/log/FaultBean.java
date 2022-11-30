package com.chindeo.repository.data.livedata.log;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.data.model.setting.MQTTStatusEnum;

/**
 * 插件状态bean
 */
public class FaultBean {
    @JSONField(name = "timestamp")
    public String timestamp;       //本次上报的时间戳
    @JSONField(name = "appType")
    public String appType;         //主应用类型
    @JSONField(name = "isBackground")
    public boolean isBackground;   //主应用是否处于后台
    @JSONField(name = "isMainActivity")
    public boolean isMainActivity; //主应用是否在主界面
    @JSONField(name = "isEmptyBed")
    public boolean isEmptyBed;     //床旁是否是空床

    @JSONField(name = "mqtt")
    public FaultItem mqtt = new FaultItem();
    @JSONField(name = "interf")
    public FaultItem interf = new FaultItem();
    @JSONField(name = "call")
    public FaultItem call = new FaultItem();
    @JSONField(name = "face")
    public FaultItem face = new FaultItem();
    @JSONField(name = "iptv")
    public FaultItem iptv = new FaultItem();

    /**
     * -1 未安装
     * 0  未启动
     * 1  已就绪
     */
    public static class FaultItem {
        public FaultItem() {
            code = "999";
            reason = "未赋值";
        }

        @JSONField(name = "code")
        public String code;
        @JSONField(name = "reason")
        public String reason;


        public static FaultItem getFaultItem(String code) {
            return getFaultItem(code, null);
        }

        public static FaultItem getFaultItem(String code, String reason) {
            FaultItem item = new FaultItem();
            MQTTStatusEnum status = MQTTStatusEnum.getStatus(code);
            switch (status) {
                case WAITING:
                    item.code = MQTTStatusEnum.WAITING.statusCode;
                    item.reason = reason != null ? reason : MQTTStatusEnum.WAITING.desc;
                    return item;
                case ONLINE:
                    item.code = MQTTStatusEnum.ONLINE.statusCode;
                    item.reason = reason != null ? reason : MQTTStatusEnum.ONLINE.desc;
                    return item;
                case OFFLINE:
                    item.code = MQTTStatusEnum.OFFLINE.statusCode;
                    item.reason = reason != null ? reason : MQTTStatusEnum.OFFLINE.desc;
                    return item;
                default:
                    item.code = MQTTStatusEnum.UN_KNOW.statusCode;
                    item.reason = reason != null ? reason : MQTTStatusEnum.UN_KNOW.desc;
                    return item;
            }
        }

        public static FaultItem httpCode(String code, String reason) {
            FaultItem item = new FaultItem();
            item.code = code;
            item.reason = reason;
            return item;
        }


    }
}
