package com.chindeo.repository.resources;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chindeo.repository.data.api.DeviceApi;
import com.chindeo.repository.data.api.LicenseApi;
import com.chindeo.repository.data.api.TokenApi;
import com.chindeo.repository.data.api.UpgradeApi;
import com.chindeo.repository.data.api.UrlApi;
import com.chindeo.repository.data.livedata.DeviceInfoLiveData;
import com.chindeo.repository.data.model.params.CipherTokenParams;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.CipherTokenBean;
import com.chindeo.repository.data.model.response.LicenseTokenBean;
import com.chindeo.repository.data.model.response.UrlCollection;
import com.chindeo.repository.data.model.response.device.AudioVideoConfigBean;
import com.chindeo.repository.data.model.response.device.DeviceVersionInfoBean;
import com.chindeo.repository.data.model.response.device.MqttConfigBean;
import com.chindeo.repository.data.model.response.upgrade.PluginBean;
import com.chindeo.repository.data.model.response.upgrade.PrimaryAppBean;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.mmkv.impl.SettingHostCache;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

public class ConfigRepository {

    private volatile static ConfigRepository INSTANCE = null;
    private static String appDynamicUrl = "";
    private static List<String> invalidJsonUrl = new ArrayList<>();

    public ConfigRepository() {
    }

    public static void initDynamicUrl(String configJson) {
        appDynamicUrl = configJson;
        SettingHostCache.cacheConfigJson(configJson);
    }

    public static void initDynamicUrl(String configJson, String configJsonDefault) {
        appDynamicUrl = configJsonDefault;
        SettingHostCache.cacheConfigJson(configJson);
    }

    public static ConfigRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (ConfigRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConfigRepository();
                }
            }
        }
        return INSTANCE;
    }


    public Flowable<CipherTokenBean> initToken() {
        return getLicenseToken()
                .compose(new ResultFlowableTransformer<>())
                .flatMap((Function<LicenseTokenBean, Publisher<CipherTokenBean>>) licenseTokenBean -> getCipherToken(new CipherTokenParams(DeviceInfoLiveData.getDeviceId(), licenseTokenBean.token))
                        .compose(new ResultFlowableTransformer<>()));
    }

    /**
     * 获取APP动态链接列表
     *
     * @return {@link UrlCollection}
     */
    public Flowable<UrlCollection> getAppDynamicUrl() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(UrlApi.class)
                .get(SettingHostCache.cacheConfigJson())
                .compose(new ResultFlowableTransformer<>())
                .map(new Function<ResponseBody, UrlCollection>() {
                    @Override
                    public UrlCollection apply(@NonNull ResponseBody responseBody) throws Exception {
                        return JSON.parseObject(new String(responseBody.bytes()), UrlCollection.class);
                    }
                });
    }

    /**
     * 获取 LincenseToken
     *
     * @return {@link LicenseTokenBean}
     */
    public Flowable<LicenseTokenBean> getLicenseToken() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(LicenseApi.class)
                .getLicenseToken()
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取api请求头的Token
     *
     * @param params {@link CipherTokenParams}
     * @return {@link CipherTokenBean}
     */
    public Flowable<CipherTokenBean> getCipherToken(CipherTokenParams params) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(TokenApi.class)
                .getCipherToken(RequestContent.create(params))
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取当前设备的主应用包信息
     *
     * @param
     * @return {@link PrimaryAppBean}
     */
    public Flowable<PrimaryAppBean> getAppUpgrade(String deviceId) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(UpgradeApi.class)
                .getAppUpgrade(deviceId)
                .compose(new HttpResultFlowableTransformer<>());
    }

    /**
     * 获取主应用的插件列表信息
     *
     * @param primaryPackage
     * @param env
     * @return
     */
    public Flowable<List<PluginBean>> getPlugInList(String primaryPackage, String env) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(UpgradeApi.class)
                .getPlugInList(primaryPackage, env)
                .compose(new ResultFlowableTransformer<>())
                .map(new Function<ResponseBody, List<PluginBean>>() {
                    @Override
                    public List<PluginBean> apply(@NonNull ResponseBody s) throws Exception {
                        return JSON.parseObject(new String(s.bytes()), new TypeReference<List<PluginBean>>() {
                        });
                    }
                });
    }


    public Flowable<AudioVideoConfigBean> getAudioVideoConfig() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getAudioVideoConfig(RequestContent.createRepositoryParams())
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<MqttConfigBean> getMqttConfig() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getMqttConfig()
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<DeviceVersionInfoBean> getDeviceVersionInfo(int versionCode) {
        RequestContent params = RequestContent.createRepositoryParams();
        params.put("version", versionCode);
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getVersionInfo(params)
                .compose(new HttpResultFlowableTransformer<>());
    }


    public Flowable<String> getTrustCallList( String locCode) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getTrustCallList()
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Boolean isVolume(){
        String test="http://10.0.0.35/static/json/config.json";

        return isBdSz() || isZh() || islh() || TextUtils.equals(test,SettingHostCache.cacheConfigJson());
    }

    //北大深圳
    public Boolean isBdSz(){
        String configUrl = SettingHostCache.cacheConfigJson();
        Log.v("TAG", "configUrl isBdSz==  " + configUrl);
        String url="http://172.16.173.120:8088/static/json/config.json";
        String test="http://10.0.0.40/static/json/config.json";
//        return true;
        return TextUtils.equals(url,configUrl);
    }
    //珠海
    public Boolean isZh(){
        String configUrl = SettingHostCache.cacheConfigJson();
        Log.v("TAG", "configUrl isZh ==  " + configUrl);
        String url = "http://172.16.16.61:8088/static/json/config.json";
        String test="http://10.0.0.35/static/json/config.json";
        return TextUtils.equals(url,configUrl) ;
    }
    //罗湖
    public Boolean islh(){
        String configUrl = SettingHostCache.cacheConfigJson();
        Log.v("TAG", "configUrl isZh ==  " + configUrl);
        String url = "http://170.168.25.69/static/json/config.json";
        String test = "http://192.168.1.102/static/json/config.json";
        return TextUtils.equals(url,configUrl) || TextUtils.equals(test,configUrl);
    }


}
