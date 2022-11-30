package com.chindeo.repository.mmkv.impl;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.chindeo.repository.mmkv.ICache;
import com.chindeo.repository.mmkv.impl.UrlCache;

import java.text.MessageFormat;

public class SettingHostCache {

    private static final String KEY_PATH = "host_key_";


    public static final String KEY_API_CONFIG_URL = KEY_PATH + "env_config";                     // 环境域名配置json地址
//    public static final String KEY_API_URL = KEY_PATH + "api";                     // api接口
    public static final String KEY_WEB_SOCKET = KEY_PATH + "websocket";            //web socket主机环境
    public static final String KEY_WEB_SOCKET_TYPE = KEY_PATH + "websocket_type"; // 用于存取的web socket的类型 tcp还是ws
    public static final String KEY_WEB_RES = KEY_PATH + "web_res";                // 静态资源的环境
    public static final String KEY_DNS_ENABLE = KEY_PATH + "dns_enable";                //是否禁用DNS

    public static final String KEY_HOST_MALL = KEY_PATH + "mall_host";                // 商城域名


    private static ICache getCache() {
        return ICache.settings();
    }

    public static void clear() {
//        getCache().remove(KEY_API_URL);
        getCache().remove(KEY_WEB_RES);
        getCache().remove(KEY_WEB_SOCKET);
    }

    public static void cacheConfigJson(String url) {
        getCache().put(KEY_API_CONFIG_URL, url);
    }

    public static String cacheConfigJson() {
        return getCache().getString(KEY_API_CONFIG_URL, "");
    }

//    public static void cacheApiHost(String url) {
//        getCache().put(KEY_API_URL, url);
//    }

    public static String cacheFormatApiHost() {
        return getUrl(cacheApiHost());
    }

    public static String cacheFormatWebResHost() {
        return getUrl(cacheWebRes());
    }

    public static String cacheApiHost() {
//        return UrlCache.getUrlConfig().getApiUrl();
//        String cache = getCache().getString(KEY_API_URL);
//        Log.d("---------", "cacheUrl apiBaseUrl ->> " + cache);
//        if (!TextUtils.isEmpty(cache)){
//
//        }
        if (UrlCache.getUrlConfig()!=null) {
            return UrlCache.getUrlConfig().getApiUrl();
        }else{
            return null;
        }
    }

    public static String cacheApiIptv(){
        String url=null;
        if (UrlCache.getUrlConfig()!=null) {
            url= UrlCache.getUrlConfig().getIptvUrl();
        }
        if (url!=null&&url.contains(":")){ //有端口号
            url=url.substring(0,url.indexOf(":"));
        }else if (url!=null&&url.contains("/")){
            url=url.substring(0,url.indexOf("/"));
        }
        return  url;
    }


    public static void cacheWebSocket(String url) {
        getCache().put(KEY_WEB_SOCKET, url);
    }

    public static String cacheWebSocket() {
        String cache = getCache().getString(KEY_WEB_SOCKET);
        if (UrlCache.getUrlConfig() != null) {
            return TextUtils.isEmpty(cache) ? UrlCache.getUrlConfig().getMqttUrl() : cache;
        }
        return getCache().getString(KEY_WEB_SOCKET);
    }

    public static void cacheWebSocketType(boolean isWsType) {
        getCache().put(KEY_WEB_SOCKET_TYPE, isWsType ? "ws" : "tcp");
    }

    public static String cacheWebSocketType() {
        return getCache().getString(KEY_WEB_SOCKET_TYPE, "ws");
    }


    public static void cacheWebRes(String url) {
        getCache().put(KEY_WEB_RES, url);
    }

    public static String cacheWebRes() {
        String cache = getCache().getString(KEY_WEB_RES);
        if (UrlCache.getUrlConfig() != null) {
            return TextUtils.isEmpty(cache) ? UrlCache.getUrlConfig().getResUrl() : cache;
        }
        return getCache().getString(KEY_WEB_RES);
    }

    public static void cacheDnsEnable(boolean enable) {
        getCache().put(KEY_DNS_ENABLE, enable);
    }

    public static boolean cacheDnsEnable() {
        return getCache().getBoolean(KEY_DNS_ENABLE, false);
    }

    public static void cacheMallHostUrl(@Nullable String url) {
        if (TextUtils.isEmpty(url)) {
            getCache().remove(KEY_HOST_MALL);
        } else {
            getCache().put(KEY_HOST_MALL, url);
        }
    }

    public static String cacheMallHost() {
        return getUrl(getCache().getString(KEY_HOST_MALL));
    }


    private static String getUrl(String baseUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            return null;
        }
        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        if (!baseUrl.startsWith("http://") && !baseUrl.startsWith("https://")) {
            return MessageFormat.format("http://{0}", baseUrl);
        } else {
            return baseUrl;
        }
    }
}
