package com.chindeo.repository.util;


import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.chindeo.repository.RepositoryComponent;
import com.chindeo.repository.data.livedata.nurses.NursesMainDataLiveData;
import com.chindeo.repository.data.model.common.AppConfig;
import com.chindeo.repository.data.model.common.ConfigInterface;
import com.chindeo.repository.data.model.response.device.AudioVideoConfigBean;
import com.chindeo.repository.data.model.response.device.MqttConfigBean;
import com.chindeo.repository.mmkv.impl.AppModuleCache;
import com.chindeo.repository.resources.ConfigRepository;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.observers.DisposableSingleObserver;


public class AppConfigManager {


    private volatile static HashMap<String, ConfigInterface> INSTANCE = null;

    public static <T extends ConfigInterface> T get(Class clazz) {
        return get(clazz.getSimpleName());
    }

    public static <T extends ConfigInterface> T get(String configName) {
        if (INSTANCE == null) {
            throw new NullPointerException(AppConfigManager.class.getSimpleName() + " must be use init()");
        }
        if (INSTANCE.get(configName) != null) {
            return (T) INSTANCE.get(configName);
        } else {
            throw new NullPointerException(configName + " that config name not found!!!");
        }
    }

    public static AppConfig get() {
        if (INSTANCE == null) {
            throw new NullPointerException(AppConfigManager.class.getSimpleName() + " must be use init()");
        }
        return (AppConfig) INSTANCE.get(AppConfig.class.getSimpleName());
    }

    public static void init(@NonNull ConfigInterface... appConfigArray) {
        INSTANCE = new HashMap<>();
        for (ConfigInterface appConfig : appConfigArray) {
            INSTANCE.put(appConfig.configSimpleName(), appConfig);
        }
        if (RepositoryComponent.isMain()) {

        }
    }

    public static void initPluginConfigData() {
        // 预留初始化config接口
        initAudioVideoConfig();
        initMqttConfig();
    }

    public static void initAudioVideoConfig() {
        LogUtils.d("请求获取AudioVideoConfig配置");
        ConfigRepository repository = ConfigRepository.getInstance();
        repository.getAudioVideoConfig()
                .singleOrError()
                .subscribe(new DisposableSingleObserver<AudioVideoConfigBean>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull AudioVideoConfigBean audioVideoConfigBean) {
                        AppModuleCache.cacheAudioVideoConfig(audioVideoConfigBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
//                        AppModuleCache.cacheAudioVideoConfig(null);
                        e.printStackTrace();
                    }
                });
    }

    public static Flowable<String> initCallList(){
        ConfigRepository repository = ConfigRepository.getInstance();
        return repository
                .getTrustCallList(NursesMainDataLiveData.getLocCode());
    }

    public static void initMqttConfig() {
        LogUtils.e("请求获取MQTT配置");
        ConfigRepository repository = ConfigRepository.getInstance();
        repository.getMqttConfig()
                .singleOrError()
                .subscribe(new DisposableSingleObserver<MqttConfigBean>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull MqttConfigBean configBean) {
                        AppModuleCache.cacheMqttConfig(configBean);
                    }

                    @Override
                    public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                        AppModuleCache.cacheMqttConfig(null);
                        e.printStackTrace();
                    }
                });
    }


}
