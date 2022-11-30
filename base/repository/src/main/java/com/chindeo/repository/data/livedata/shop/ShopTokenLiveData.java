package com.chindeo.repository.data.livedata.shop;

import android.text.TextUtils;

import androidx.lifecycle.MutableLiveData;

import com.chindeo.repository.data.model.response.mall.MallLoginBean;

/**
 * 商城token
 * 实体和护工服务共用
 * Created by xiemaohui on 2022/5/16
 */
public enum ShopTokenLiveData {
    INSTANCE;

    private MutableLiveData<MallLoginBean> liveData;

    ShopTokenLiveData() {
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
