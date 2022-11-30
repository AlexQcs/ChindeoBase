package com.chindeo.repository.resources;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chindeo.repository.data.api.DeviceApi;
import com.chindeo.repository.data.api.LicenseApi;
import com.chindeo.repository.data.api.TokenApi;
import com.chindeo.repository.data.api.UpgradeApi;
import com.chindeo.repository.data.api.UrlApi;
import com.chindeo.repository.data.livedata.DeviceInfoLiveData;
import com.chindeo.repository.data.model.call.CallLogBean;
import com.chindeo.repository.data.model.common.IPage;
import com.chindeo.repository.data.model.params.CipherTokenParams;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.params.device.DeviceCallHistoryParams;
import com.chindeo.repository.data.model.response.CipherTokenBean;
import com.chindeo.repository.data.model.response.LicenseTokenBean;
import com.chindeo.repository.data.model.response.UrlCollection;
import com.chindeo.repository.data.model.response.device.AudioVideoConfigBean;
import com.chindeo.repository.data.model.response.device.DeviceCallRecordBean;
import com.chindeo.repository.data.model.response.device.DeviceNoticeTopBean;
import com.chindeo.repository.data.model.response.device.DeviceStatusBean;
import com.chindeo.repository.data.model.response.device.MqttConfigBean;
import com.chindeo.repository.data.model.response.upgrade.PluginBean;
import com.chindeo.repository.data.model.response.upgrade.PrimaryAppBean;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableFunction;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.mmkv.impl.CallCache;
import com.chindeo.repository.mmkv.impl.UrlCache;
import com.chindeo.repository.util.AppConfigManager;
import com.chindeo.repository.util.http.ApiEnv;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class DeviceRepository {

    private volatile static DeviceRepository INSTANCE = null;

    public DeviceRepository() {
    }

    public static DeviceRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (DeviceRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DeviceRepository();
                }
            }
        }
        return INSTANCE;
    }


    public Flowable<DeviceStatusBean> getDeviceStatusList(String locCode, int type) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getDeviceStatusList(locCode, type)
                .subscribeOn(Schedulers.io())
                .flatMap(new HttpResultFlowableFunction<>());
    }

    /**
     * // 获取音视频呼叫记录
     */
    public Flowable<List<DeviceCallRecordBean>> getCallRecordList(DeviceCallHistoryParams params, IPage page) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getCallRecordList(RequestContent.createRepositoryParams(params, page))
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<List<CallLogBean>> getCallRecordListCache() {
        return Flowable.just(CallCache.getCallLogToday())
                .compose(new ResultFlowableTransformer<>());
    }


    /**
     * 获取公告
     *
     * @param type
     * @return
     */
    public Flowable<DeviceNoticeTopBean> getTopNotice(String type) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getTopNotice(type)
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<Long> getServerTime() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(DeviceApi.class)
                .getServerTime()
                .compose(new HttpResultFlowableTransformer<>());
    }

}
