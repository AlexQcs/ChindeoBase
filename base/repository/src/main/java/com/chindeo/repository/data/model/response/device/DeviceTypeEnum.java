package com.chindeo.repository.data.model.response.device;

import java.util.Arrays;
import java.util.List;

public enum DeviceTypeEnum {

    //1:护理白板，2:床旁屏，3:门旁屏，4:护士站主机，5:通用WebApp，6:XXX3L墨水瓶，7:X3L墨水瓶，8:L3墨水瓶，9:输液泵，10:PDA，11:网关，12:院内探视，13:安卓白板，14:走廊屏，15:输液监控仪，16:床垫
    HLBB(1, "护理白板"),
    CPP(2, "床旁屏"),
    MPP(3, "门旁屏"),
    HSZZJ(4, "护士站主机"),
    TYWEB(5, "通用WebApp"),
    XXX3L_MSP(6, "XXX3L墨水瓶"),
    X3L_MSP(7, "X3L墨水瓶"),
    L3_MSP(8, "L3墨水瓶"),
    SYG(9, "输液泵"),
    PDA(10, "PDA"),
    WG(11, "网关"),
    YNTS(12, "院内探视"),
    AZBB(13, "安卓白板"),
    ZLP(14, "走廊屏"),
    SYJKY(15, "输液监控仪"),
    CD(16, "床垫"),
    OTHER(-1, "其他设备")
    ;

    public final int type;
    public final String typeName;

    DeviceTypeEnum(int type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public List<DeviceTypeEnum> getChindeoAndroidDeviceList(){
        return Arrays.asList(HLBB, CPP, HSZZJ, MPP, ZLP);
    }

    public DeviceTypeEnum get(int type){
        for (DeviceTypeEnum value : values()) {
            if (value.type == type){
                return value;
            }
        }
        return OTHER;
    }
}
