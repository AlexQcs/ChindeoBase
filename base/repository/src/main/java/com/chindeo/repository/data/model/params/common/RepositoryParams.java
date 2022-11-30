package com.chindeo.repository.data.model.params.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.data.livedata.DeviceInfoLiveData;

import java.io.Serializable;

public class RepositoryParams implements Parcelable, Serializable {

    @JSONField(name = "deviceId")
    public String deviceId;

    public RepositoryParams() {
        deviceId = DeviceInfoLiveData.getDeviceId();
    }

    protected RepositoryParams(Parcel in) {
        deviceId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(deviceId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RepositoryParams> CREATOR = new Creator<RepositoryParams>() {
        @Override
        public RepositoryParams createFromParcel(Parcel in) {
            return new RepositoryParams(in);
        }

        @Override
        public RepositoryParams[] newArray(int size) {
            return new RepositoryParams[size];
        }
    };
}