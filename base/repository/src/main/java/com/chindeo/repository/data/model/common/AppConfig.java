package com.chindeo.repository.data.model.common;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppConfig implements ConfigInterface {

    public String getUserAgent() {
        return "";
    }

    public List<Integer> getGuiImageList() {
        return new ArrayList<>();
    }

    @JSONField(name = "buglyAppId")
    public String buglyAppId; // https://bugly.qq.com/v2/product/apps/017587c12a?pid=1

    @JSONField(name = "skinId")
    public int skinId = 0;

    @JSONField(name = "service_id")
    public String serviceId;

    @JSONField(name = "guiClsName")
    public String guiClsName;


    @JSONField(name = "mainClsName")
    public String mainClsName;

    @JSONField(name = "enterClsName")
    public String enterClsName;

    /**
     * Http.UA頭應用標識
     *
     * @return
     */
    @JSONField(name = "userAgentFlag")
    public String userAgentFlag;

    @JSONField(name = "jsName")
    public String jsName = "";

    @JSONField(name = "envRes")
    public int envRes;

    /**
     *
     */
    @JSONField(name = "apiVersion")
    public String apiVersion = "";

    /**
     * 设备类型id 与后台统一
     * // 获取设备详情列表 type 1=护理白板 2=床旁 3=门旁 4=护士站主机 14=走廊屏
     * 1:护理白板，2:床旁屏，3:门旁屏，4:护士站主机，5:通用WebApp，6:XXX3L墨水瓶，7:X3L墨水瓶，8:L3墨水瓶，9:输液泵，10:PDA，11:网关，12:院内探视，13:安卓白板，14:走廊屏，15:输液监控仪，16:床垫
     */
    @JSONField(name = "deviceType")
    public int deviceType = 1;

    @JSONField(name = "mmkvMultiProcessMode")
    public boolean mmkvMultiProcessMode = true;

    @Override
    public String configSimpleName() {
        return "AppConfig";
    }

}
