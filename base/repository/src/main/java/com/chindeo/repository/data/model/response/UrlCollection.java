package com.chindeo.repository.data.model.response;

import com.alibaba.fastjson.annotation.JSONField;

public class UrlCollection {

    /*{
        "res":"10.0.0.23:80",
            "api":"10.0.0.23:2333",
            "mqtt":"10.0.0.23:8083",
            "ftp":{
        "username": "admin",
                "password": "Chindeo",
                "host": "10.0.0.23",
                "port": "21",
                "frequency": 5
    },
        "voip":"10.0.0.23:5060",
            "iptv":"10.0.0.23:80/static/iptv/channel.json",
            "ntp":"local.chindeo.com:123"
    }*/


    @JSONField(name = "res")
    public String resUrl;
    @JSONField(name = "api")
    public String apiUrl;
    @JSONField(name = "mqtt")
    public String mqttUrl;
    @JSONField(name = "voip")
    public String voipUrl;
    @JSONField(name = "iptv")
    public String iptvUrl;
    @JSONField(name = "ntp")
    public String ntpUrl;
    @JSONField(name = "ftp")
    public FtpInfo ftpInfo;
    @JSONField(name = "rtc")
    public String rtc;

    public static class FtpInfo {

        @JSONField(name = "username")
        public String username;
        @JSONField(name = "password")
        public String password;
        @JSONField(name = "host")
        public String host;
        @JSONField(name = "port")
        public String port;
        @JSONField(name = "frequency")
        public int frequency; // 频率 分钟

    }

    @Override
    public String toString() {
        return "UrlCollection{" +
                "resUrl='" + resUrl + '\'' +
                ", apiUrl='" + apiUrl + '\'' +
                ", mqttUrl='" + mqttUrl + '\'' +
                ", voipUrl='" + voipUrl + '\'' +
                ", iptvUrl='" + iptvUrl + '\'' +
                ", ntpUrl='" + ntpUrl + '\'' +
                ", ftpInfo=" + ftpInfo +
                ", rtc='" + rtc + '\'' +
                '}';
    }
}
