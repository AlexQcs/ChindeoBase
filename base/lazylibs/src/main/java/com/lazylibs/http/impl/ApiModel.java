package com.lazylibs.http.impl;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.lazylibs.http.util.RetrofitUtil;

import org.reactivestreams.Publisher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.X509TrustManager;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;

public class ApiModel<T> {

    private String baseUrl;

    private Class<T> cls;

    private T api;

    private List<IApiModel> iApiModelList;

    private OkHttpClient okHttpClient;

    public ApiModel(String baseUrl, Class<T> cls) {
        this(RetrofitUtil.getOkHttpClient(), baseUrl, cls);
    }

    public ApiModel(String baseUrl, Class<T> cls, IApiModel... iApiModels) {
        this(RetrofitUtil.getOkHttpClient(), baseUrl, cls, iApiModels);
    }

    public ApiModel(OkHttpClient okHttpClient, String baseUrl, Class<T> cls, IApiModel... iApiModels) {
        this.baseUrl = baseUrl;
        this.cls = cls;
        this.iApiModelList = new ArrayList<>();
        this.okHttpClient = okHttpClient;
        this.iApiModelList.addAll(Arrays.asList(iApiModels));
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public T getApi() {
        if (api == null) {
            //noinspection unchecked
            api = (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, newInvocationHandler());
        }
        return api;
    }

    private ApiModel<T> getApiModelThis() {
        return this;
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            final X509TrustManager trustAllCert = new X509TrustManager() {

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }

            };
            okHttpClient = RetrofitUtil.getOkHttpClient();
        }
        return okHttpClient;
    }

    private InvocationHandler newInvocationHandler() {
        return new InvocationHandler() {
            public Object invoke(Object proxy, final Method method, final Object[] args) throws Throwable {
                T service = RetrofitUtil.get(baseUrl, getOkHttpClient()).create(cls);
                return Flowable.just(service)
                        .flatMap(new Function<Object, Publisher<?>>() {
                            public Publisher<?> apply(Object o) throws Exception {
                                try {
                                    Object obj = method.invoke(o, args);
                                    if (obj instanceof Flowable) {
                                        return (Flowable) obj;
                                    }

                                    if (obj instanceof Observable) {
                                        return ((Observable) obj).toFlowable(BackpressureStrategy.BUFFER);
                                    }

                                    if (obj instanceof Single) {
                                        return ((Single) obj).toFlowable();
                                    }

                                    if (obj instanceof Completable) {
                                        return ((Completable) obj).toFlowable();
                                    }

                                    if (obj instanceof Maybe) {
                                        return ((Maybe) obj).toFlowable();
                                    }
                                } catch (IllegalAccessException var3) {
                                    var3.printStackTrace();
                                } catch (InvocationTargetException var4) {
                                    var4.printStackTrace();
                                }
                                return Flowable.just(method.invoke(o, args));
                            }
                        })
                        .doOnNext(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) {
                                try {
                                    for (IApiModel iApiModel : iApiModelList) {
                                        if (iApiModel != null) {
                                            iApiModel.onSuccess(getApiModelThis(), o);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) {
                                try {
                                    for (IApiModel iApiModel : iApiModelList) {
                                        if (iApiModel != null) {
                                            iApiModel.onError(getApiModelThis(), throwable);
                                        }
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiModel<?> apiModel = (ApiModel<?>) o;

        //noinspection EqualsReplaceableByObjectsCall
        if (baseUrl != null ? !baseUrl.equals(apiModel.baseUrl) : apiModel.baseUrl != null)
            return false;
        //noinspection EqualsReplaceableByObjectsCall
        if (cls != null ? !cls.equals(apiModel.cls) : apiModel.cls != null) return false;
        //noinspection EqualsReplaceableByObjectsCall
        return okHttpClient != null ? okHttpClient.equals(apiModel.okHttpClient) : apiModel.okHttpClient == null;
    }

    @Override
    public int hashCode() {
        int result = baseUrl != null ? baseUrl.hashCode() : 0;
        result = 31 * result + (cls != null ? cls.hashCode() : 0);
        result = 31 * result + (okHttpClient != null ? okHttpClient.hashCode() : 0);
        return result;
    }
}
