package com.chindeo.repository.data.model.params.setting;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class DevicesStatusParams implements Parcelable {

    public List<Integer> deviceList=new ArrayList<>();
    public long duration;
    public String status;
    public DevicesStatusParams(){

    }

    public DevicesStatusParams(Parcel in) {
        duration = in.readLong();
        status = in.readString();
    }

    public static final Creator<DevicesStatusParams> CREATOR = new Creator<DevicesStatusParams>() {
        @Override
        public DevicesStatusParams createFromParcel(Parcel in) {
            return new DevicesStatusParams(in);
        }

        @Override
        public DevicesStatusParams[] newArray(int size) {
            return new DevicesStatusParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(duration);
        dest.writeString(status);
    }
}
