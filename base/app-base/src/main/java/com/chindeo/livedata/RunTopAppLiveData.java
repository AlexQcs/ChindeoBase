package com.chindeo.livedata;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.chindeo.repository.contants.AppModule;
import com.chindeo.repository.data.livedata.PrimaryAppInfoLiveData;
import com.chindeo.repository.mmkv.impl.AppSettingCache;

/**
 * 当前置顶应用
 */
public enum RunTopAppLiveData {

    INSTANCE;

    private MutableLiveData<String> mutableLiveData;

    RunTopAppLiveData() {
    }

    public MutableLiveData<String> getLiveData() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
        }
        return mutableLiveData;
    }

    public static long getLastRunPrimaryTime(){
       return AppSettingCache.getRunPrimaryAppTime();
    }

    public static void currentTop(String packageName) {
        AppModule primaryApp = PrimaryAppInfoLiveData.INSTANCE.getPrimaryApp();
        if (primaryApp!=null) {
            if (packageName.equals(primaryApp.packageName)){
                AppSettingCache.updateRunPrimaryAppTime();
            }
        }
        RunTopAppLiveData.INSTANCE.getLiveData().postValue(packageName);
    }

    @Deprecated
    public static String getCurrentTop(Context context) {
        return RunTopAppLiveData.INSTANCE.getLiveData().getValue();
    }

    public static String getCurrentTop() {
        return RunTopAppLiveData.INSTANCE.getLiveData().getValue();
    }

}
