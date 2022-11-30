package com.chindeo.repository.mmkv.impl;

import android.text.TextUtils;
import android.util.Log;

import com.chindeo.repository.contants.FilePathConstants;
import com.chindeo.repository.data.model.call.AppCallVo;
import com.lazylibs.util.FastJsonUtils;
import com.lazylibs.util.FileIOUtils;


/**
 * 音视频的配置
 */
public class CallConfigUtils {

    public static AppCallVo getAppCallVo() {
        String callConfig = FileIOUtils.readFile2String(FilePathConstants.
                getDir(FilePathConstants.APP_CALL_CONFIG));
        if (!TextUtils.isEmpty(callConfig)) {
            return FastJsonUtils.toBean(callConfig, AppCallVo.class);
        }
        return new AppCallVo(true, null);
    }


    /**
     * @param isBoost 麦克风增强
     */
    public static void setCallMicBoost(boolean isBoost) {
        AppCallVo appCallVo = getAppCallVo();
        if (appCallVo == null) {
            appCallVo = new AppCallVo(isBoost, null);
        } else {
            appCallVo.micBoost = isBoost;
        }
        FileIOUtils.writeFileFromString(FilePathConstants.
                getDir(FilePathConstants.APP_CALL_CONFIG), FastJsonUtils.getBeanToJson(appCallVo));
    }

    public static boolean getCallMicBoost() {
        AppCallVo appCallVo = getAppCallVo();
        if (appCallVo != null) {
            return appCallVo.micBoost;
        }
        return true;
    }

    /**
     * @param isTrust 设置无人托管
     */
    public static void setTrust(boolean isTrust) {

        AppCallVo appCallVo = getAppCallVo();
        if (appCallVo != null) {
            appCallVo.isTrust = isTrust;
            FileIOUtils.writeFileFromString(FilePathConstants.
                    getDir(FilePathConstants.APP_CALL_CONFIG), FastJsonUtils.getBeanToJson(appCallVo));
        }

    }

    //获取是否无人托管
    public static boolean getTrust() {
        AppCallVo appCallVo = getAppCallVo();
        if (appCallVo != null) {
            return appCallVo.isTrust;
        }
        return false;
    }

    /**
     * @param tip 更新无人接听提示语
     */
    public static void setNoResponse(String tip) {
        AppCallVo appCallVo = getAppCallVo();
        if (appCallVo == null) {
            appCallVo = new AppCallVo(true, tip);
        } else {
            appCallVo.noResponse = tip;
        }
        FileIOUtils.writeFileFromString(FilePathConstants.
                getDir(FilePathConstants.APP_CALL_CONFIG), FastJsonUtils.getBeanToJson(appCallVo));

    }

    /**
     * @return 获取无人接听提示语
     */
    public static String getNoResponse() {
        AppCallVo appCallVo = getAppCallVo();
        if (appCallVo != null) {
            Log.e("TAG", "获取无人接听提示语 == " + appCallVo.noResponse );
            return appCallVo.noResponse;
        }
        return null;
    }


}
