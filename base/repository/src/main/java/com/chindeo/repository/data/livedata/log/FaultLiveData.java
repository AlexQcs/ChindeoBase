package com.chindeo.repository.data.livedata.log;


import com.lazylibs.util.TimeUtils;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

public enum FaultLiveData {

    INSTANCE;

    private MutableLiveData<FaultBean> liveData;

    FaultLiveData() {
    }

    public MutableLiveData<FaultBean> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<FaultBean>() {
                @Nullable
                @Override
                public FaultBean getValue() {
                    return super.getValue() == null ? new FaultBean() : super.getValue();
                }
            };
        }
        return liveData;
    }

    public static void update(FaultBean faultBean) {
        faultBean.timestamp= TimeUtils.getNowString();
        FaultLiveData.INSTANCE.getLiveData().postValue(faultBean);
    }

    public static FaultBean getValue() {
        return FaultLiveData.INSTANCE.getLiveData().getValue();
    }


}
