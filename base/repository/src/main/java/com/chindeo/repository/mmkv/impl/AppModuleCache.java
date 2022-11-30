package com.chindeo.repository.mmkv.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chindeo.repository.contants.AppModule;
import com.chindeo.repository.data.model.response.device.AudioVideoConfigBean;
import com.chindeo.repository.data.model.response.device.MqttConfigBean;
import com.chindeo.repository.data.model.response.nurses.RingtonesAccountsBean;
import com.chindeo.repository.mmkv.ICache;

import java.util.List;

/**
 * 模块配置信息
 */
public class AppModuleCache {

    private static final String KEY_PATH = "key_";
    private static final String KEY_APP_PRIMARY_MODULE = KEY_PATH + "primary_module";
    private static final String KEY_APP_AUDIO_VIDEO_CONFIG = "audio_video_config"; // 音视频连接配置
    private static final String KEY_APP_MQTT_CONFIG = "mqtt_config"; // mqtt连接配置
    private static final String KEY_APP_ENV = "app_env"; // app包环境


    private static ICache getCache() {
        return ICache.module();
    }

    /**
     * 记录当前设备的主appModule
     *
     * @param appModule {@link AppModule}
     */
    public static void setPrimaryModule(AppModule appModule) {
        getCache().put(KEY_APP_PRIMARY_MODULE, appModule.name());
    }

    public static AppModule getPrimaryAppModule() {
        return AppModule.valueOf(getCache().getString(KEY_APP_PRIMARY_MODULE, ""));
    }

    public static void setAppPackageEnv(String env) {
        getCache().put(KEY_APP_ENV, env);
    }

    public static String getAppPackageEnv() {
        return getCache().getString(KEY_APP_ENV, "");
    }

    public static void cacheAudioVideoConfig(AudioVideoConfigBean bean) {
        getCache().put(KEY_APP_AUDIO_VIDEO_CONFIG, JSON.toJSONString(bean));
    }

    public static AudioVideoConfigBean getAudioVideoConfigBean() {
        String json = getCache().getString(KEY_APP_AUDIO_VIDEO_CONFIG);
        AudioVideoConfigBean config = null;
        try {
            config = JSON.parseObject(json, AudioVideoConfigBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void cacheMqttConfig(MqttConfigBean bean) {
        if (bean == null) {
            getCache().remove(KEY_APP_MQTT_CONFIG);
        } else {
            getCache().put(KEY_APP_MQTT_CONFIG, JSON.toJSONString(bean));
        }
    }

    public static MqttConfigBean getMqttConfigBean() {
        String json = getCache().getString(KEY_APP_MQTT_CONFIG);
        if (TextUtils.isEmpty(json)){
            return null;
        }
        MqttConfigBean config = null;
        try {
            config = JSON.parseObject(json, MqttConfigBean.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return config;
    }

    private static final String KEY_APP_AUDIO_VIDEO_TRUST_LIST = "KEY_APP_AUDIO_VIDEO_TRUST_LIST"; // 音视频连接配置

    public static String getAudioVideoTrustConfigBean() {
        String json = getCache().getString(KEY_APP_AUDIO_VIDEO_TRUST_LIST);
        return json;
    }

    private static final String KEY_APP_AUDIO_VIDEO_SELECT = "KEY_APP_AUDIO_VIDEO_SELECT"; // 音视频连接配置
    public static String getAudioVideoSelect() {
        String json = getCache().getString(KEY_APP_AUDIO_VIDEO_SELECT);
        return json;
    }
    public static void cacheAudioVideoTrustConfig(String bean) {
        getCache().put(KEY_APP_AUDIO_VIDEO_TRUST_LIST, bean);
    }



    private static final String RINGTONES_AND_COMMUNICATION_ACCOUNTS = "RINGTONES_AND_COMMUNICATION_ACCOUNTS"; // 铃声
    public static List<RingtonesAccountsBean> cacheRingtones() {
        String json = getCache().getString(RINGTONES_AND_COMMUNICATION_ACCOUNTS);

        if(!TextUtils.isEmpty(json)) {
            List<RingtonesAccountsBean> beans = JSON.parseObject(json, new TypeReference<List<RingtonesAccountsBean>>() {});
            return beans;
        }
        return null;
    }
    public static void cacheRingtones(String bean) {
        getCache().put(RINGTONES_AND_COMMUNICATION_ACCOUNTS, bean);
    }
   /* public static void setAppVersion(AppModule wApp, String version) {
        getCache().put(KEY_APP_VERSION + wApp, version);
    }

    public static String getAppVersion(AppModule wApp) {
        return getCache().getString(KEY_APP_VERSION + wApp, "none");
    }*/

}
