package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.voice.WatchCallBean;
import com.chindeo.repository.data.model.response.watch.WatchDeviceInfo;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface ServiceApi {


    @POST(ApiConstants.VOICE_PARSE_IDENTIFY)
    Flowable<HttpResult<String>> voiceParseIdentify(@Body RequestContent params);

    @POST(ApiConstants.VOICE_PARSE_CONTROL)
    Flowable<HttpResult<String>> voiceParseControl(@Body RequestContent params);

    @POST(ApiConstants.POST_VOICE_COMMAND_CONTROL)
    Flowable<HttpResult<String>> voiceControl(@Body RequestContent params);

    @POST(ApiConstants.GET_VOICE_CALL_INFO)
    Flowable<HttpResult<WatchCallBean>> getCallInfoFromVoice(@Body RequestContent params);

    @GET(ApiConstants.WATCH_DEVICE_INFO)
    Flowable<HttpResult<WatchDeviceInfo>> getWatchDeviceInfo();
}
