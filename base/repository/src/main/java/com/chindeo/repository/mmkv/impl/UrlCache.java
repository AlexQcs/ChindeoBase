package com.chindeo.repository.mmkv.impl;

import android.os.Environment;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.chindeo.repository.contants.FilePathConstants;
import com.chindeo.repository.contants.HostMode;
import com.chindeo.repository.data.UrlConfig;
import com.chindeo.repository.data.model.response.UrlCollection;
import com.chindeo.repository.mmkv.ICache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class UrlCache {
    private static final String SP_FILE = "url_constants";

    private static final String SP_KEY_URL_CONFIG = "sp_key_url_config";

    private static final String SP_KEY_URL_CONFIG_CACHE_TIME = "sp_key_url_config_cache_time";

    private static final long URL_CONFIG_DELAY_CACHE_TIME = 15 * 60 * 1000;

    private static UrlConfig config;

    public static final String FILE_NAME_CONFIG_HOST = "config_host.json";
    public static final String SP_KEY_HOST_MODE = "sp_key_host_mode";


    /**
     * 初始化动态链接的配置信息
     *
     * @param urlCollection 动态链接的内容
     */
    public static void initUrlConfig(UrlCollection urlCollection) {
        ICache iCache = getCache();
        if (urlCollection == null){
            iCache.remove(SP_KEY_URL_CONFIG);
            iCache.remove(SP_KEY_URL_CONFIG_CACHE_TIME);
            return;
        }
        String json = JSON.toJSONString(urlCollection);
        iCache.put(SP_KEY_URL_CONFIG, json);
        iCache.put(SP_KEY_URL_CONFIG_CACHE_TIME, System.currentTimeMillis());
        //////////////
        UrlCache.config = new UrlConfig(urlCollection == null ?
                JSON.parseObject(json, UrlCollection.class) : urlCollection);
    }

    /**
     * 获取动态链接的配置信息
     */
    public static UrlConfig getUrlConfig() {
//        if (UrlCache.config == null) { // 启动器域名更换会不知道，所以每次都拿
            ICache iCache = getCache();
            String json = iCache.getString(SP_KEY_URL_CONFIG);
//            Log.v("TAG", "getUrlConfig -- " + json);
            if (TextUtils.isEmpty(json)) {
                return null;
            }
            UrlCache.config = new UrlConfig(JSON.parseObject(json, UrlCollection.class));
//        }
        return UrlCache.config;
    }

    public static String findSdCardRecord() {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        File sdFile = new File(FilePathConstants.getDir(FilePathConstants.APP_PRIMARY_FILE_DIR), FILE_NAME_CONFIG_HOST);
        // 判断是否存在该文件
        if (sdFile.exists()) {
            // 打开文件输入流
            FileInputStream fileR = null;
            try {
                fileR = new FileInputStream(sdFile);
                BufferedReader reads = new BufferedReader(
                        new InputStreamReader(fileR));
                String st = null;
                while ((st = reads.readLine()) != null) {
                    stringBuilder.append(st);
                }
                fileR.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public static boolean isCacheTime() {
        ICache iCache = getCache();
        long cacheTime = iCache.getLong(SP_KEY_URL_CONFIG_CACHE_TIME);
        return !(cacheTime == -1 || Math.abs(System.currentTimeMillis() - cacheTime) > URL_CONFIG_DELAY_CACHE_TIME);
    }

    public static ICache getCache() {
        return ICache.create(SP_FILE);
    }

    public static void foreReload() {
        ICache iCache = getCache();
        iCache.put(SP_KEY_URL_CONFIG_CACHE_TIME, System.currentTimeMillis() - URL_CONFIG_DELAY_CACHE_TIME - 1000);
    }


    /**
     * 设置host配置模式
     * @param hostMode {@link HostMode}
     */
    public static void setHostMode(HostMode hostMode) {
        getCache().put(SP_KEY_HOST_MODE, hostMode.name());
    }
    public static HostMode getHostMode() {
        return HostMode.valueOf(getCache().get(SP_KEY_HOST_MODE));
    }


}
