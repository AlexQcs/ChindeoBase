package com.chindeo.repository.mmkv.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.chindeo.repository.RepositoryComponent;
import com.chindeo.repository.data.model.common.DeviceInfo;
import com.chindeo.repository.mmkv.ICache;
import com.chindeo.repository.util.AppConfigManager;
import com.lazylibs.util.AppUtils;

import java.util.Arrays;
import java.util.List;

/**
 * App设置
 */
public class AppSettingCache {
    private static final String SP_KEY_IS_FIRST = "is_first";
    private static final String SP_KEY_APP_KEEP_LONG_LIGHT = "app_keep_long_light"; // 常亮
    private static final String SP_KEY_REBOOT_TIME = "last_reboot_time_1";// 最后重启时间
    private static final String SP_KEY_RUN_PRIMARY_TIME = "last_run_primary_time_1";// 最后回到主app的时间
    private static final String SP_KEY_DEVICE = "device_id";// 记录deviceId防止变更
    private static final String SP_KEY_CALL_MIC_BOOST = "call_mic_boost";//麦克风增强
    private static final String SP_KEY_CURRENT_SKIN = "current_skin_id";
    private static final String SP_KEY_SETTING_PW_OPEN = "manager_pw_open"; // 管理员页面进入需要密码

    private static ICache getCache() {
        return ICache.settings();
    }

    public static void setSkin(int skinId) {
        getCache().put(SP_KEY_CURRENT_SKIN, skinId);
    }

    public static int getCurrentSkin() {
        return getCache().getInt(SP_KEY_CURRENT_SKIN, AppConfigManager.get().skinId);
    }

    public static void deviceInfo(DeviceInfo info) {
        getCache().put(SP_KEY_DEVICE, JSON.toJSONString(info));
    }

    public static DeviceInfo deviceInfo() {
        String json = getCache().getString(SP_KEY_DEVICE);
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        DeviceInfo info = null;
        try {
            info = JSON.parseObject(json, DeviceInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }


    public static boolean firstOpen() {
        return getCache().getBoolean(SP_KEY_IS_FIRST + AppUtils.getAppVersionName(RepositoryComponent.getInstance()), true);
    }

    public static void firstOpen(boolean isFirst) {
        getCache().put(SP_KEY_IS_FIRST + AppUtils.getAppVersionName(RepositoryComponent.getInstance()), isFirst);
    }

    /**
     * 第一次打开页面引导用
     *
     * @param pageTag
     * @return
     */
    public static boolean firstOpen(String pageTag) {
        return getCache().getBoolean(SP_KEY_IS_FIRST + pageTag + AppUtils.getAppVersionName(RepositoryComponent.getInstance()), true);
    }

    /**
     * 第一次打开页面引导用
     *
     * @param pageTag
     * @return
     */
    public static void firstOpen(String pageTag, boolean isFirst) {
        getCache().put(SP_KEY_IS_FIRST + pageTag + AppUtils.getAppVersionName(RepositoryComponent.getInstance()), isFirst);
    }

    /**
     * 常亮
     */
    public static boolean isKeepLongLight() {
        return getCache().getBoolean(SP_KEY_APP_KEEP_LONG_LIGHT, true);
    }

    /**
     * 常亮设置
     */
    public static void isKeepLongLight(boolean b) {
        getCache().put(SP_KEY_APP_KEEP_LONG_LIGHT, b);
    }


    /**
     * 字符串列表转换成用逗号隔开的字符串<br/>
     * List<String> -> String
     * <p>
     * {"3","1","2"} ==> "312"
     * </p>
     *
     * @param list
     * @return
     */
    public static String setListToString(List<String> list) {
        if (list != null && list.size() > 0) {
            String[] strArr = list.toArray(new String[0]);
            String _res = Arrays.toString(strArr);
            return _res.substring(1, _res.length() - 1).replace(" ", "");
            // .replace(",", "");
        }
        return null;
    }


    public static void updateRebootTime() {
        getCache().put(SP_KEY_REBOOT_TIME, System.currentTimeMillis());
    }

    public static long getLastRebootTime() {
        return getCache().getLong(SP_KEY_REBOOT_TIME);
    }

    public static void updateRunPrimaryAppTime() {
        getCache().put(SP_KEY_RUN_PRIMARY_TIME, System.currentTimeMillis());
    }

    public static long getRunPrimaryAppTime() {
        return getCache().getLong(SP_KEY_RUN_PRIMARY_TIME);
    }

    public static boolean settingLoginPw(){
        return getCache().getBoolean(SP_KEY_SETTING_PW_OPEN, true);
    }

    public static boolean settingLoginPwToggle(){
        boolean status = !getCache().getBoolean(SP_KEY_SETTING_PW_OPEN);
        getCache().put(SP_KEY_SETTING_PW_OPEN, status);
        return status;
    }

}