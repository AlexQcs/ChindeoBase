package com.chindeo.repository.data.model.nurses;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

//很多接口返回 当前设备的音视频信息 和 所绑定的护士站 音视频信息
public class DeviceUserBean implements Parcelable {

    @JSONField(name = "id")
    public String id; //"10",

    @JSONField(name = "userName")
    public String phoneNumber; //"1112",

    @JSONField(name = "password")
    public String password; //"Chindeo",

    @JSONField(name = "displayName")
    public String displayName; //"护士站主机"

    @JSONField(name = "deviceIp")
    public String deviceIp; //"10.0.0.25"

    @JSONField(name = "autoAnswer")
    public String autoAnswer; //0不开启自动接听，1开启自动接听

    @JSONField(name = "music")
    public String music; // 铃声序号 null 0 表示 默认，

    public DeviceUserBean() {
    }

    protected DeviceUserBean(Parcel in) {
        id = in.readString();
        phoneNumber = in.readString();
        password = in.readString();
        displayName = in.readString();
        deviceIp = in.readString();
        autoAnswer = in.readString();
        music = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(phoneNumber);
        dest.writeString(password);
        dest.writeString(displayName);
        dest.writeString(deviceIp);
        dest.writeString(autoAnswer);
        dest.writeString(music);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DeviceUserBean> CREATOR = new Creator<DeviceUserBean>() {
        @Override
        public DeviceUserBean createFromParcel(Parcel in) {
            return new DeviceUserBean(in);
        }

        @Override
        public DeviceUserBean[] newArray(int size) {
            return new DeviceUserBean[size];
        }
    };

    public boolean isEmpty(){
        return phoneNumber == null;
    }
}
