package com.chindeo.repository.data.livedata;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.chindeo.repository.mmkv.impl.AccessTokenCache;
import com.chindeo.repository.mmkv.impl.SettingHostCache;

public enum AccessTokenLiveData {

    INSTANCE;

    private MutableLiveData<String> liveData;

    AccessTokenLiveData() {
    }

    public MutableLiveData<String> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<String>(){
                @Override
                public void postValue(String value) {
                    super.postValue(value);
                    AccessTokenCache.INSTANCE.setAccessToken(value);
                }
            };
        }
        return liveData;
    }

    public static void update(String token) {
        if (!TextUtils.isEmpty(token) && !token.equals(getToken())) {
            INSTANCE.getLiveData().postValue(token);
        }
    }

    public static String getToken(){
        return INSTANCE.getLiveData().getValue();
    }

    public static boolean isLoad(){
        return !TextUtils.isEmpty(getToken());
    }

    public static void clear(){
        INSTANCE.getLiveData().postValue(null);
    }
}
