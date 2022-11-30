package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.pda.PdaDetailBean;
import com.chindeo.repository.data.model.response.HttpResult;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface PdaApi {

    @GET(ApiConstants.GET_DETAIL_INFO)
    Flowable<HttpResult<PdaDetailBean>> getPdaDetailInfo(@QueryMap RequestContent params);
}
