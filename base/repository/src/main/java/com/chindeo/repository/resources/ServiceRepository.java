package com.chindeo.repository.resources;

import com.chindeo.repository.data.api.ServiceApi;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.params.service.VoiceCallInfoParams;
import com.chindeo.repository.data.model.params.service.VoiceControlParams;
import com.chindeo.repository.data.model.params.service.VoiceParseParams;
import com.chindeo.repository.data.model.response.voice.WatchCallBean;
import com.chindeo.repository.data.model.response.watch.WatchDeviceInfo;
import com.chindeo.repository.data.transform.flowable.HttpResultFlowableTransformer;
import com.chindeo.repository.util.http.imp.HttpUtilImp;
import com.lazylibs.http.HttpUtilFactory;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class ServiceRepository {

    private volatile static ServiceRepository INSTANCE = null;

    public static ServiceRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (ServiceRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceRepository();
                }
            }
        }
        return INSTANCE;
    }

    public Flowable<String> voiceParseIdentify(VoiceParseParams parseParams) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ServiceApi.class)
                .voiceParseIdentify(RequestContent.createRepositoryParams(parseParams))
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<String> voiceParseControl(VoiceParseParams parseParams) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ServiceApi.class)
                .voiceParseControl(RequestContent.createRepositoryParams(parseParams))
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<String> voiceControl(VoiceControlParams parseParams) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ServiceApi.class)
                .voiceControl(RequestContent.createRepositoryParams(parseParams))
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<WatchCallBean> getCallInfoFromVoice(VoiceCallInfoParams parseParams) {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ServiceApi.class)
                .getCallInfoFromVoice(RequestContent.createRepositoryParams(parseParams))
                .compose(new HttpResultFlowableTransformer<>());
    }

    public Flowable<WatchDeviceInfo> getWatchDeviceInfo() {
        return HttpUtilFactory.create(HttpUtilImp.class)
                .getApi(ServiceApi.class)
                .getWatchDeviceInfo()
                .compose(new HttpResultFlowableTransformer<>());
    }

}
