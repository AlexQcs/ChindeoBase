package com.chindeo.repository.data;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.data.model.response.UrlCollection;

import java.text.MessageFormat;

public class UrlConfig {

    @JSONField(name = "urls")
    private UrlCollection urls;

    public UrlConfig(@NonNull UrlCollection urls) {
        this.urls = urls;
    }

    public String getFormatApiUrl() {
        return getUrl(urls.apiUrl);
    }

    public String getApiUrl() {
        return urls.apiUrl;
    }

    public String getIptvUrl(){
        return urls.iptvUrl;
    }

    public String getFormatResUrl() {
        return getUrl(urls.resUrl);
    }

    public String getResUrl() {
        return urls.resUrl;
    }

    public String getMqttUrl() {
        return urls.mqttUrl;
    }

    public UrlCollection.FtpInfo getFtpInfo() {
        return urls.ftpInfo;
    }
    public String getRtc() {
        return urls.rtc;
    }

    private String getUrl(String baseUrl) {
        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        if (!baseUrl.startsWith("http://")) {
            return MessageFormat.format("http://{0}", baseUrl);
        } else {
            return baseUrl;
        }
    }

    public String getUrlByTag(TAG tag) {
        String url = "";
        switch (tag) {
            case API_URL:
                url = getFormatApiUrl();
                break;
            case RES_URL:
                url = getFormatResUrl();
                break;
            case MQTT_URL:
                url = getMqttUrl();
                break;
        }
        return url;
    }

    public enum TAG {
        API_URL("apiurl"),
        RES_URL("resurl"),
        MQTT_URL("mqtturl"),
        ;

        TAG(String val) {
            this.val = val;
        }

        private final static String LAST_URL_FORMAT = "last_request_%s";

        public String key() {
            return String.format(LAST_URL_FORMAT, val);
        }

        String val;
    }


}
