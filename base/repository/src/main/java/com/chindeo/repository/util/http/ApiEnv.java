package com.chindeo.repository.util.http;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.chindeo.repository.BuildConfig;
import com.chindeo.repository.RepositoryComponent;
import com.chindeo.repository.data.model.common.Flavor;
import com.chindeo.repository.mmkv.ICache;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApiEnv {

    public static final String NAME = "API_ENV";

    public static final String KEY = "api_key_";
    // 当前环境配置的名称
    public static final String KEY_CURRENT_ENV = KEY + "current_env";
    // 所有环境配置
    public static final String KEY_ALL_ENV = KEY + "all_env";
    // 记录上一次构建的记录
    public static final String KEY_LAST_BUILD_TYPE = KEY + "last_build_type";

    public static ICache getCache() {
        return ICache.create(NAME);
    }

    public static boolean isOfficial(String flavor) {
        return TextUtils.isEmpty(flavor) && flavor.contains("official");
    }

    // 获取UserAgent环境key
    public static String getEnvKey(String flavor) {
        if (isOfficial(flavor)) {
            return "official";
        }
        return flavor == null ? "" : flavor;
    }

    private static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    @SuppressLint("ApplySharedPref")
    public static void init(Application app, String buildDefaultFlavorName, List<Flavor> list) {
        ICache.onCreate(app);
        String currentFlavorName = currentFlavorName(app);
        String buildFlavorName = TextUtils.isEmpty(currentFlavorName) ? buildDefaultFlavorName : currentFlavorName;
        String currentBuildType = BuildConfig.BUILD_TYPE;
        Log.i("ApiEnv", "init: current " + currentFlavorName);
        Log.i("ApiEnv", "init: build " + buildFlavorName);
        //这里为了区分是不是正式环境包，避免多环境测试导致的问题。
        //noinspection ConstantConditions
        if (currentBuildType.equals("release")) {
            // 避免正式包多次重置
            String buildLastBuildType = getSp(app).getString(KEY_LAST_BUILD_TYPE, "");
            if (!buildLastBuildType.equals(currentBuildType)) {
                // 强制清理
                cleanApplicationData(app);
                ICache.onCreate(app);
                getSp(app).edit().putString(KEY_LAST_BUILD_TYPE, currentBuildType).commit();
            }
        }
        // init方法需要手动编写这行代码，请勿使用update方法
        getSp(app).edit().putString(KEY_CURRENT_ENV, buildFlavorName).commit();
        updateFlavorList(list);
    }

    public static boolean contains(Flavor flavor) {
        Context context = RepositoryComponent.getInstance().getApplicationContext();
        return currentFlavorName(context).equals(flavor.name);
    }

    public static String currentFlavorName(Context context) {
        // 这里处理不返回默认值，有外部去控制默认值
        return getSp(context).getString(KEY_CURRENT_ENV, "");
    }

    @SuppressLint("ApplySharedPref")
    public static boolean update(Context context, String flavorName) {
        String currentFlavorName = currentFlavorName(context);
        if (currentFlavorName.equals(flavorName)) {
            return false;
        }
        // 切换成功清理本地数据
        cleanApplicationData(context);
        // 这里一定要用SharePreference去保存
        getSp(context).edit().putString(KEY_CURRENT_ENV, flavorName).commit();
        Log.i("ApiEnv", "save: " + flavorName);
        return true;
    }

    public static boolean update(String flavorName) {
        return update(RepositoryComponent.getInstance(), flavorName);
    }

    public static void updateFlavorList(List<Flavor> list) {
        getCache().put(KEY_ALL_ENV, JSON.toJSONString(list));
    }

    public static List<Flavor> flavorList() {
        String listJson = getCache().getString(KEY_ALL_ENV, "[]");
        return JSON.parseArray(listJson, Flavor.class);
    }

    public static Flavor flavor() {
        String currentFlavorName = currentFlavorName(RepositoryComponent.getInstance());
        String listJson = getCache().getString(KEY_ALL_ENV, "[]");
        List<Flavor> list = JSON.parseArray(listJson, Flavor.class);
        Flavor flavor = new Flavor(currentFlavorName);
        int index = list.indexOf(flavor);
        if (index != -1) {
            flavor = list.get(index);
        }
        return flavor;
    }

    public static boolean isChina() {
        return true;//flavor() != Flavor.overseas;
    }

    /**
     * 清除本应用所有的数据 * * @param context * @param filepath
     */
    @SuppressLint("SdCardPath")
    private static void cleanApplicationData(Context context) {
        try {
            deleteAll(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                deleteAll(context.getExternalCacheDir());
            }
            deleteAll(context.getFilesDir());
            deleteAll(new File("/data/data/" + context.getPackageName() + "/databases"));
            deleteAll(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
            deleteAll(new File("/data/data/" + context.getPackageName() + "/app_webview"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 * * @param directory
     */
    private static void deleteAll(File directory) {
        if (directory != null && directory.exists()) {
            if (directory.isDirectory()) {
                File[] files = directory.listFiles();
                if (files != null && files.length > 0) {
                    for (File file : files) {
                        deleteAll(file);
                    }
                }
            }
            directory.delete();
        }
    }

}