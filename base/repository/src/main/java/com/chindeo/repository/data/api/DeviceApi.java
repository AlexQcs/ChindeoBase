package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.device.DeviceCallRecordBean;
import com.chindeo.repository.data.model.response.device.DeviceNoticeTopBean;
import com.chindeo.repository.data.model.response.device.DeviceStatusBean;
import com.chindeo.repository.data.model.response.device.DeviceVersionInfoBean;
import com.chindeo.repository.data.model.response.device.MqttConfigBean;
import com.chindeo.repository.data.model.response.device.AudioVideoConfigBean;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DeviceApi {

    @GET(ApiConstants.API_DEVICE_USER)
    Flowable<HttpResult<AudioVideoConfigBean>> getAudioVideoConfig(@QueryMap RequestContent params);

    @GET(ApiConstants.API_MQTT_INFO)
    Flowable<HttpResult<MqttConfigBean>> getMqttConfig();

    @GET(ApiConstants.API_DEVICE_STATUS_LIST)
    Flowable<HttpResult<DeviceStatusBean>> getDeviceStatusList(@Query("locCode") String locCode, @Query("type") int deviceType);

    @GET(ApiConstants.API_DEVICE_CALL_HISTORY)
    Flowable<HttpResult<List<DeviceCallRecordBean>>> getCallRecordList(@QueryMap RequestContent params);

    @GET(ApiConstants.API_TOP_NOTICE)
    Flowable<HttpResult<DeviceNoticeTopBean>> getTopNotice(@Query("type") String type);

    @GET(ApiConstants.API_VERSION_INFO)
    Flowable<HttpResult<DeviceVersionInfoBean>> getVersionInfo(@QueryMap RequestContent params);

    @GET(ApiConstants.API_SERVER_TIME)
    Flowable<HttpResult<Long>> getServerTime();

    @GET(ApiConstants.API_CALL_LIST)
    Flowable<HttpResult<String>> getTrustCallList();
}
