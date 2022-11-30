package com.chindeo.repository.data.api;

import com.alibaba.fastjson.JSONObject;
import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.nurses.WardListBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.HttpResult;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WardApi {


    @GET(ApiConstants.API_GET_DEVICES_LIST)
    Flowable<HttpResult<List<WardListBean>>> getWardList(@Query("locCode") String locCode, @Query("type") int type);

   @POST(ApiConstants.API_POST_DEVICES_STATUS)
    Flowable<ResponseBody> postWardStatus(@Body RequestContent params);



}
