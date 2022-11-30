package com.chindeo.repository.util.http.imp;

import static android.util.Log.INFO;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.chindeo.repository.contants.HttpResultCode;
import com.chindeo.repository.data.UrlConfig;
import com.chindeo.repository.data.api.LicenseApi;
import com.chindeo.repository.data.api.UpgradeApi;
import com.chindeo.repository.data.api.UrlApi;
import com.chindeo.repository.data.api.VisitApi;
import com.chindeo.repository.data.exception.ResponseErrorException;
import com.chindeo.repository.data.livedata.AccessTokenLiveData;
import com.chindeo.repository.data.livedata.log.FaultBean;
import com.chindeo.repository.data.livedata.log.FaultLiveData;
import com.chindeo.repository.data.model.response.CipherTokenBean;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.mmkv.impl.AccessTokenCache;
import com.chindeo.repository.mmkv.impl.SettingHostCache;
import com.chindeo.repository.resources.ConfigRepository;
import com.chindeo.repository.util.http.SSLSocketFactoryCompat;
import com.lazylibs.BuildConfig;
import com.lazylibs.http.HttpUtils;
import com.lazylibs.http.impl.ApiModel;
import com.lazylibs.http.impl.IApiModel;
import com.lazylibs.http.impl.IHttpUtil;
import com.lazylibs.http.logging.Level;
import com.lazylibs.http.logging.LoggingInterceptor;
import com.lazylibs.http.util.RetrofitUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableSingleObserver;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtilImp implements IHttpUtil {

    private static volatile AtomicBoolean updateFlag;

    private final List<ApiModel<?>> failApiList;

    private IApiModel iApiModel;

    private final Map<Class<?>, UrlConfig.TAG> URL_TAG;

    public HttpUtilImp() {
        URL_TAG = new HashMap<>(16);
        failApiList = Collections.synchronizedList(new ArrayList<>());
    }

    @Override
    public <T> T getApi(@NonNull Class<T> cls, String... pathKeys) {
        String baseUrl = getBaseUrl(cls);
        OkHttpClient okHttpClient;
        if (cls.equals(UrlApi.class)) {
            okHttpClient = RetrofitUtil.getOkHttpClient()
                    .newBuilder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(15, TimeUnit.SECONDS)
                    .build();
        } else {
            okHttpClient = createOkHttpClientBuilder(cls).build();
        }
        ApiModel<T> apiModel = HttpUtils.create(okHttpClient, cls, baseUrl, getIApiModel());
        if (isNeedUpdate()) {
            if (failApiList.contains(apiModel)) {
                if (URL_TAG.containsKey(cls)) {
                    UrlConfig.TAG tag = URL_TAG.get(cls);
                    if (tag != null) {
                        baseUrl = getBaseUrl(cls);
                        apiModel = HttpUtils.create(okHttpClient, cls, baseUrl, getIApiModel());
                        setUpdateFlag(false);
                    }
                }
            }
        }
        return apiModel.getApi();
    }

    public <T> OkHttpClient.Builder  createOkHttpClientBuilder(Class<T> service ) {
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

        return RetrofitUtil.newDefaultOkHttpBuilder()
                .addInterceptor(new Interceptor() {

                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {

                        Request originalRequest = chain.request();
//                        String userAgent = AppConfigManager.get().getUserAgent();
                        Request.Builder builder = originalRequest.newBuilder();
                        String token = AccessTokenCache.INSTANCE.getAccessToken();
                        if (service == VisitApi.class){ //添加旧版商城token
//                            if (VisitTokenLiveData.getToken()!=null){
//                                builder.addHeader("X-Token", VisitTokenLiveData.getToken());
//                            }
//                            builder.addHeader("AuthType","4");//默认传4
//                            builder.addHeader("Mac", DeviceInfoLiveData.getDeviceId());

                        }else
                            if (!TextUtils.isEmpty(token)) {
                            builder.addHeader("X-Token", token);
                        }

                        Request request = builder.build();
                        return chain.proceed(request);
                    }
                })
                .addInterceptor(new LoggingInterceptor.Builder()
                                .loggable(BuildConfig.DEBUG) //是否开启日志打印
                                .setLevel(Level.BASIC) //打印的等级
                                .log(INFO) // 打印类型
                                .request("okRequest") // request的Tag
                                .response("okResponse") //Response的Tag
                                .build())
                .sslSocketFactory(new SSLSocketFactoryCompat(trustAllCert), trustAllCert)
                .hostnameVerifier(new HostnameVerifier() {
                    @SuppressLint("BadHostnameVerifier")
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
    }

    /**
     * 下一次错误域名更新
     *
     * @param isSwitch 是否切换
     */
    public static void setUpdateFlag(boolean isSwitch) {
        if (updateFlag == null) {
            updateFlag = new AtomicBoolean(false);
        }
        updateFlag.set(isSwitch);
    }

    private static boolean isNeedUpdate() {
        if (updateFlag == null) {
            updateFlag = new AtomicBoolean(false);
        }
        return updateFlag.get();
    }

    public IApiModel getIApiModel() {
        if (iApiModel == null) {
            iApiModel = new IApiModel() {

                private DisposableSingleObserver<CipherTokenBean> tokenDis;

                @Override
                public void onError(ApiModel apiModel, Throwable e) {
                    if (!failApiList.contains(apiModel)) {
                        failApiList.add(apiModel);
                    }
                    FaultBean faultBean = FaultLiveData.getValue();
                    if (e instanceof ResponseErrorException) {
                        ResponseErrorException responseErrorException = (ResponseErrorException) e;
                        faultBean.interf = FaultBean.FaultItem.httpCode(String.valueOf(responseErrorException.state())
                                , responseErrorException.error());

                    } else {
                        faultBean.interf = FaultBean.FaultItem.httpCode("99", e.getClass().getName());
                    }
                    FaultLiveData.update(faultBean);


                }

                @Override
                public void onSuccess(ApiModel apiModel, Object data) {
                    failApiList.remove(apiModel);
                    if (data instanceof HttpResult) {
                        FaultBean faultBean = FaultLiveData.getValue();
                        HttpResult httpResult = (HttpResult) data;
                        faultBean.interf = FaultBean.FaultItem.httpCode(String.valueOf(httpResult.code)
                                , "ok");

                        if (HttpResultCode.isTokenInvalid(httpResult.code())) {
                            AccessTokenLiveData.update(null);
                            if (tokenDis == null) {
                                LogUtils.w("token失效。重新获取token");
                                tokenDis = ConfigRepository.getInstance().initToken()
                                        .doFinally(new Action() {
                                            @Override
                                            public void run() throws Exception {
                                                tokenDis = null;
                                            }
                                        })
                                        .singleOrError()
                                        .subscribeWith(new DisposableSingleObserver<CipherTokenBean>() {
                                            @Override
                                            public void onSuccess(@io.reactivex.annotations.NonNull CipherTokenBean cipherTokenBean) {
                                                LogUtils.d("*重新获取token成功 -> " + cipherTokenBean.token);
                                                if (TextUtils.isEmpty(cipherTokenBean.token)) { // todo 后台还没改 非200
                                                    return;
                                                }
                                                AccessTokenLiveData.update(cipherTokenBean.token);
                                            }

                                            @Override
                                            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                                                e.printStackTrace();
                                            }
                                        });
                            }
                        }
                    }
                }

            };
        }
        return iApiModel;
    }

    public String getBaseUrl(Class api) {
        StringBuilder baseUrlBuilder = new StringBuilder();
        String baseUrl;
        if (api.equals(LicenseApi.class)
                || api.equals(UpgradeApi.class)) {
            baseUrl = SettingHostCache.cacheFormatWebResHost();
        } else if(api.equals(VisitApi.class)){
//            baseUrl="http://diancan.t.chindeo.com/"; // 旧版商城域名
            baseUrl="http://demand.chindeo.com"; //探视预约域名
        } else {
            baseUrl = SettingHostCache.cacheFormatApiHost();
        }
        baseUrl = TextUtils.isEmpty(baseUrl) ? "https://www.urlApi.com/" :baseUrl;
        baseUrlBuilder.append(baseUrl);
        return getApiUrl(baseUrlBuilder.toString());
    }

    private String getUrl(String baseUrl) {
        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        return baseUrl;
    }

    private String getApiUrl(String baseUrl) {
        return getUrl(baseUrl);// + API_VERSION;
    }
}