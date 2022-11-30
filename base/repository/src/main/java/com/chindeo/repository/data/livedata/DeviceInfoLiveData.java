package com.chindeo.repository.data.livedata;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.chindeo.repository.RepositoryComponent;
import com.chindeo.repository.contants.DeviceType;
import com.chindeo.repository.data.model.common.DeviceInfo;
import com.chindeo.repository.mmkv.impl.AppSettingCache;
import com.chindeo.repository.util.AndroidDeviceUtil;
import com.chindeo.repository.util.CommandUtil;

public enum DeviceInfoLiveData {

    INSTANCE;

    public static final String DEVICE_BRAND_YLD = "rockchip";
    public static final String DEVICE_BRAND_TELPO = "TELPO";  //启动器安装护士站主机应用失败，就是因为品牌判断失败。
    public static final String DEVICE_BRAND_ANDROID = "Android";  //启动器安装护士站主机应用失败，就是因为品牌判断失败。

    private MutableLiveData<DeviceInfo> liveData;

    DeviceInfoLiveData() {
    }


    public MutableLiveData<DeviceInfo> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<DeviceInfo>();
        }
        return liveData;
    }


    public DeviceInfo init() {
        DeviceInfo deviceInfo = AppSettingCache.deviceInfo();
        if (null == deviceInfo || TextUtils.isEmpty(deviceInfo.id)) {
            deviceInfo = new DeviceInfo();
            deviceInfo.id = getDeviceIdInit();
            String brand = android.os.Build.BRAND;
            if (DEVICE_BRAND_YLD.toUpperCase().equals(brand.toUpperCase())) {
                deviceInfo.type = DeviceType.YLD;
            } else if (DEVICE_BRAND_TELPO.toUpperCase().equals(brand.toUpperCase()) || DEVICE_BRAND_ANDROID.toUpperCase().equals(brand.toUpperCase())) {
                deviceInfo.type = DeviceType.TB;
            } else {
                deviceInfo.type = DeviceType.OTHER;
            }
            getLiveData().postValue(deviceInfo);
            AppSettingCache.deviceInfo(deviceInfo);
        }
        return deviceInfo;
    }

    private static DeviceInfo getDeviceInfo() {
        if (INSTANCE.getLiveData().getValue() == null) {
//            throw new RuntimeException("必须先调用init()");
        }
        return INSTANCE.liveData.getValue();
    }

    public static String getDeviceId() {
        if (getDeviceInfo() == null || TextUtils.isEmpty(getDeviceInfo().id)) {
            return INSTANCE.init().id;
        }
        return getDeviceInfo().id;
    }

    public static DeviceType getDeviceType() {
        if (getDeviceInfo() == null || TextUtils.isEmpty(getDeviceInfo().id)) {
            return INSTANCE.init().type;
        }
        return getDeviceInfo().type;
    }

    //所有App通用逻辑，为设备唯一码赋值
    public static String getDeviceIdInit() {
        String deviceId = CommandUtil.getEthMac();
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = AndroidDeviceUtil.getMac(RepositoryComponent.getInstance());
        }
        if (!TextUtils.isEmpty(deviceId)) {
            deviceId = deviceId.toUpperCase();
            deviceId = deviceId.replace(":", "").replace("：", "");
        }
        return deviceId;
    }
}
