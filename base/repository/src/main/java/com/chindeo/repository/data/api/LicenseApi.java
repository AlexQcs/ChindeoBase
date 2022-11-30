package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.LicenseTokenBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface LicenseApi {


    /**
     * 获取授权Token
     *
     * @param
     * @return {@link LicenseTokenBean}
     */
    @GET(ApiConstants.API_GET_LICENSE)
    Flowable<HttpResult<LicenseTokenBean>> getLicenseToken();
}
