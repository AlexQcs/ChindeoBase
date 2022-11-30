package com.chindeo.repository.data.model.response.device;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class MqttConfigBean implements Parcelable, Serializable {

    @JSONField(name = "userName")
    public String userName;
    @JSONField(name = "password")
    public String password;
    @JSONField(name = "simulation")
    public String simulation;  //模拟病区

    public MqttConfigBean() {
    }

    protected MqttConfigBean(Parcel in) {
        userName = in.readString();
        password = in.readString();
        simulation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(password);
        dest.writeString(simulation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MqttConfigBean> CREATOR = new Creator<MqttConfigBean>() {
        @Override
        public MqttConfigBean createFromParcel(Parcel in) {
            return new MqttConfigBean(in);
        }

        @Override
        public MqttConfigBean[] newArray(int size) {
            return new MqttConfigBean[size];
        }
    };

    @Override
    public String toString() {
        return "MqttConfigBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", simulation='" + simulation + '\'' +
                '}';
    }
}
