package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.CipherTokenBean;
import com.chindeo.repository.data.model.response.HttpResult;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TokenApi {

    /**
     * 获取api请求头的Token
     *
     * @param params {@link com.chindeo.repository.data.model.params.CipherTokenParams}
     * @return {@link CipherTokenBean}
     */
    @POST(ApiConstants.API_GET_CIPHER_TEXT)
    Flowable<HttpResult<CipherTokenBean>> getCipherToken(@Body RequestContent params);

}
