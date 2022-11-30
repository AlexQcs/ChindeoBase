package com.chindeo.repository.util.http

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import com.chindeo.repository.data.api.LicenseApi
import com.chindeo.repository.data.api.UpgradeApi
import com.chindeo.repository.data.api.kt.MallApi
import com.chindeo.repository.data.api.kt.OrderApi
import com.chindeo.repository.data.api.kt.ShopApi
import com.chindeo.repository.data.livedata.mall.MallTokenLiveData
import com.chindeo.repository.data.livedata.order.OrderTokenLiveData
import com.chindeo.repository.data.livedata.shop.ShopTokenLiveData
import com.chindeo.repository.mmkv.impl.AccessTokenCache.getAccessToken
import com.chindeo.repository.mmkv.impl.SettingHostCache
import com.lazylibs.BuildConfig
import com.lazylibs.http.data.converter.FastJsonConverterFactory
import com.lazylibs.http.logging.Level
import com.lazylibs.http.logging.LoggingInterceptor
import com.lazylibs.http.util.RetrofitUtil
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

//管理retrofit
class RetrofitManager private constructor() {

    companion object {
        val instance: RetrofitManager by lazy { RetrofitManager() }
    }


    fun <T> create(service: Class<T>): T {
        val baseUrl = getBaseUrl(service)

        val okHttpClient = createOkHttpClientBuilder(service).build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .validateEagerly(true)
            .addConverterFactory(FastJsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()

        return retrofit.create(service)
    }


    private fun <T> createOkHttpClientBuilder(service: Class<T>): OkHttpClient.Builder {
        val trustAllCert: X509TrustManager = object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        }
        return RetrofitUtil.newDefaultOkHttpBuilder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                //                        String userAgent = AppConfigManager.get().getUserAgent();
                val builder = originalRequest.newBuilder()
                val token = getAccessToken()

                if (service == MallApi::class.java) {  //添加护工服务token
                    MallTokenLiveData.getToken()?.let { builder.addHeader("Authorization", "Bearer $it") }
                }else if (service == OrderApi::class.java) { //添加营养点餐token
                    OrderTokenLiveData.getToken()?.let{builder.addHeader("Authorization", "Bearer $it")}
                }else if (service == ShopApi::class.java) { //添加商城token
                    ShopTokenLiveData.getToken().let{ builder.addHeader("Authorization", "Bearer $it")}
                } else if (!TextUtils.isEmpty(token)) {
                    builder.addHeader("X-Token", token)
                }
                val request = builder.build()
                chain.proceed(request)
            }
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .loggable(BuildConfig.DEBUG) //是否开启日志打印
                    .setLevel(Level.BASIC) //打印的等级
                    .log(Log.INFO) // 打印类型
                    .request("okRequest") // request的Tag
                    .response("okResponse") //Response的Tag
                    .build()
            )
            .sslSocketFactory(SSLSocketFactoryCompat(trustAllCert), trustAllCert)
            .hostnameVerifier { hostname, session -> true }
    }

    private fun getBaseUrl(api: Class<*>): String {
        val baseUrlBuilder = StringBuilder()
        var baseUrl = if (api == LicenseApi::class.java || api == UpgradeApi::class.java) {
            SettingHostCache.cacheFormatWebResHost()
        } else if (api == OrderApi::class.java || api == MallApi::class.java || api == ShopApi::class.java) {
            SettingHostCache.cacheMallHost()
        } else {
            SettingHostCache.cacheFormatApiHost()
        }
        baseUrl = if (TextUtils.isEmpty(baseUrl)) "https://www.urlApi.com/" else baseUrl
        baseUrlBuilder.append(baseUrl)
        return getApiUrl(baseUrlBuilder.toString())
    }

    private fun getUrl(baseUrl: String): String {
        var baseUrl = baseUrl
        baseUrl = if (baseUrl.endsWith("/")) baseUrl else "$baseUrl/"
        return baseUrl
    }

    private fun getApiUrl(baseUrl: String): String {
        return getUrl(baseUrl) // + API_VERSION;
    }
}