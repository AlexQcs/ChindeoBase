package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.VisitApiConstants;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.nurses.NursesBedMessageBean;
import com.chindeo.repository.data.model.response.nurses.NursesVisitListBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 远程探视API
 * Created by xiemaohui on 2022/8/26
 */

public interface VisitApi {
//    //获取token
//    @POST(VisitApiConstants.API_POST_VISIT_LOGIN)
//    Flowable<HttpResult<String>> login(@Body Map<String, String> map);
//
//    @POST(VisitApiConstants.API_POST_ViSIT_REFRESH)
//    Flowable<HttpResult<String>> refresh();

    @GET(VisitApiConstants.API_GET_VISIT_TIMES)
    Flowable<HttpResult<List<NursesVisitListBean>>> getVisitTimes(@QueryMap Map<String,String> map);

    @GET(VisitApiConstants.API_GET_VISIT_PASS_TIME)
    Flowable<HttpResult<String>> visitPass(@Query("code")String code);

    @GET(VisitApiConstants.API_GET_VISIT_REJECT_TIME)
    Flowable<HttpResult<String>> visitRefuse(@Query("code")String code,@Query("message")String message);

}
