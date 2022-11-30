package com.lazylibs.http;

import android.text.TextUtils;

import com.lazylibs.http.impl.ApiModel;
import com.lazylibs.http.impl.IApiModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;

public class HttpUtils {

    private volatile static Map<Class, List<ApiModel>> apiMap;

    public static <T> ApiModel<T> create(Class<T> cls, String baseUrl) {
        return create(null, cls, baseUrl);
    }

    public static <T> ApiModel<T> create(Class<T> cls, String baseUrl, IApiModel... iApiModel) {
        return create(null, cls, baseUrl, iApiModel);
    }

    public static <T> ApiModel<T> create(OkHttpClient okHttpClient, Class<T> cls, String baseUrl, IApiModel... iApiModel) {
        List<ApiModel> list = getApiModelList(cls);
        ApiModel<T> apiModel = null;
        for (ApiModel model : list) {
            String apiBaseUrl = model.getBaseUrl();
            apiBaseUrl = TextUtils.isEmpty(apiBaseUrl) ? "" : apiBaseUrl;
            if (apiBaseUrl.equals(baseUrl)) {
                //noinspection unchecked
                apiModel = model;
                break;
            }
        }
        if (apiModel == null) {
            apiModel = new ApiModel<>(okHttpClient, baseUrl, cls, iApiModel);
        }
        checkApiModel(apiModel);
        return apiModel;
    }

    private static void checkApiModel(ApiModel model) {
        Class cls = model.getApi().getClass();
        List<ApiModel> list = getApiModelList(cls);
        if (!list.contains(model)) {
            list.add(model);
        }
        getApiMap().put(cls, list);
    }

    private static List<ApiModel> getApiModelList(Class cls) {
        List<ApiModel> list = getApiMap().get(cls);
        if (list == null) {
            list = Collections.synchronizedList(new ArrayList<>());
        }
        return list;
    }

    private static Map<Class, List<ApiModel>> getApiMap() {
        if (apiMap == null) {
            apiMap = Collections.synchronizedMap(new HashMap<>(16));
        }
        return apiMap;
    }

    public static void clear() {
        apiMap.clear();
        apiMap = null;
    }

}
