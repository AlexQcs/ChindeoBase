package com.chindeo.repository.data.model.response.voice;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

public class WatchCallBean implements Parcelable {

    @JSONField(name = "userName")
    public String phoneNumber;
    @JSONField(name = "password")
    public String password;
    @JSONField(name = "displayName")
    public String displayName;
    @JSONField(name = "deviceIp")
    public String deviceIp;
    @JSONField(name = "music")
    public String music;

    public WatchCallBean() {
    }

    protected WatchCallBean(Parcel in) {
        phoneNumber = in.readString();
        password = in.readString();
        displayName = in.readString();
        deviceIp = in.readString();
        music = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(password);
        dest.writeString(displayName);
        dest.writeString(deviceIp);
        dest.writeString(music);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WatchCallBean> CREATOR = new Creator<WatchCallBean>() {
        @Override
        public WatchCallBean createFromParcel(Parcel in) {
            return new WatchCallBean(in);
        }

        @Override
        public WatchCallBean[] newArray(int size) {
            return new WatchCallBean[size];
        }
    };
}
