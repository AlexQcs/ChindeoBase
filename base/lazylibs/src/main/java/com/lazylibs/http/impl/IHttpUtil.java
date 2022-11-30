package com.lazylibs.http.impl;

import android.text.TextUtils;

import com.lazylibs.http.HttpUtils;

import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;

public interface IHttpUtil {

    default <T> T getApi(@NonNull Class<T> cls, String... pathKeys) {
        StringBuilder baseUrlBuilder = new StringBuilder();
        baseUrlBuilder.append(getBaseUrl(cls));
        for (String pathKey : pathKeys) {
            if (!TextUtils.isEmpty(pathKey)) {
                baseUrlBuilder.append(pathKey)
                        .append("/");
            }
        }
        OkHttpClient okHttpClient = createOkHttpClientBuilder(cls).build();
        String baseUrl = baseUrlBuilder.toString();
        ApiModel<T> apiModel = HttpUtils.create(okHttpClient, cls, baseUrl, getIApiModel());
        return apiModel.getApi();
    }

    <T> String getBaseUrl(Class<T> cls);

    IApiModel getIApiModel();

    <T> OkHttpClient.Builder createOkHttpClientBuilder(Class<T> cls);

}
