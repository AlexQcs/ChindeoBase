package com.chindeo.repository.data.livedata.mall;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.chindeo.repository.data.model.response.mall.MallLoginBean;


public enum MallTokenLiveData {
    INSTANCE;


    private MutableLiveData<MallLoginBean> liveData;

    MallTokenLiveData() {
    }

    public MutableLiveData<MallLoginBean> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<>();
        }
        return liveData;
    }

    public static void update(MallLoginBean bean) {
        if (bean != null) {
            INSTANCE.getLiveData().setValue(bean);
        }
    }

    public static String getToken() {
        MallLoginBean value = INSTANCE.getLiveData().getValue();
        if (value == null) {
            return null;
        }
        return value.accessToken;
    }

    public static boolean isLoad() {
        return !TextUtils.isEmpty(getToken());
    }

    public static void clear() {
        INSTANCE.getLiveData().postValue(null);
    }
}
