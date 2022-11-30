package com.chindeo.repository.data.model.response.device;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeviceStatusListBean {

    /**
     * key DeviceType
     */
   public  Map<DeviceTypeEnum, DeviceStatusBean> dataMap;

    public DeviceStatusListBean() {
        dataMap = new LinkedHashMap<>();
    }
}
