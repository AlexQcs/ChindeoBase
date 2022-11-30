package com.chindeo.repository.data.api;

import com.chindeo.repository.data.model.params.RequestContent;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface UrlApi {

    @GET
    Flowable<ResponseBody> get(@Url String url);

    @GET
    Flowable<ResponseBody> get(@Url String url, @QueryMap Map<String, Object> map);

    @POST
    Flowable<ResponseBody> post(@Url String url);

    @POST
    @FormUrlEncoded
    Flowable<ResponseBody> post(@Url String url, @FieldMap RequestContent map);

    @PUT
    Flowable<ResponseBody> put(@Url String url);

    @DELETE
    Flowable<ResponseBody> delete(@Url String url);


}
