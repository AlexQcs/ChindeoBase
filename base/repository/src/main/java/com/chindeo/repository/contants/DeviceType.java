package com.chindeo.repository.contants;

public enum DeviceType {


    TB(0), YLD(1), OTHER(2);
    public final int value;

    DeviceType(int value) {
        this.value = value;
    }

    public static final int DEVICE_TYPE_TB = 0;   //表示本机设备是天波
    public static final int DEVICE_TYPE_YLD = 1;  //表示本机设备是亿莱顿
    public static final int DEVICE_TYPE_OTHER = 2;  //表示本机设备是其他设备


}
