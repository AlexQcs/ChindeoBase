package com.chindeo.repository.data.model.response.device;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

public class AudioVideoConfigBean implements Parcelable {

    @JSONField(name = "userName")
    public String phoneNumber;//"1112",
    @JSONField(name = "displayName")
    public String displayName;//"护士站主机"
    @JSONField(name = "music")
    public String music;//铃声序号 null 0 表示 默认，
    @JSONField(name = "autoAnswer")
    public String autoAnswer;//0不开启自动接听，1开启自动接听

    public AudioVideoConfigBean() {
    }

    //    @JSONField(name = "autoAnswer")
//    public String autoAnswer;//0不开启自动接听，1开启自动接听
//    @JSONField(name = "id")
//    public String id;//"10",

//        "userName": "5024",
//                "password": "Chindeo",
//                "displayName": "WAN-dennis",
//                "deviceIp": "10.0.0.23",
//                "music": "1"


    protected AudioVideoConfigBean(Parcel in) {
        phoneNumber = in.readString();
        displayName = in.readString();
        music = in.readString();
        autoAnswer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNumber);
        dest.writeString(displayName);
        dest.writeString(music);
        dest.writeString(autoAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AudioVideoConfigBean> CREATOR = new Creator<AudioVideoConfigBean>() {
        @Override
        public AudioVideoConfigBean createFromParcel(Parcel in) {
            return new AudioVideoConfigBean(in);
        }

        @Override
        public AudioVideoConfigBean[] newArray(int size) {
            return new AudioVideoConfigBean[size];
        }
    };

    @Override
    public String toString() {
        return "AudioVideoConfigBean{" +
                "userName='" + phoneNumber + '\'' +
                ", displayName='" + displayName + '\'' +
                ", music='" + music + '\'' +
                ", autoAnswer='" + autoAnswer + '\'' +
                '}';
    }
}
