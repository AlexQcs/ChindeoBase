package com.chindeo.repository.data.livedata.nurses;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;


/**
 * 旧版商城token
 * Created by xiemaohui on 2022/8/26
 */
public enum VisitTokenLiveData {
    INSTANCE;

    private MutableLiveData<String> liveData;

    VisitTokenLiveData() {
    }

    public MutableLiveData<String> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
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
