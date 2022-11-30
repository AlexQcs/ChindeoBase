package com.chindeo.repository.data.model.nurses;

import android.os.Parcel;
import android.os.Parcelable;

public class WardListBean implements Parcelable {

    public int deviceId;
    public int duration;
    public String end;
    public String roomCode;
    public String status;
    public boolean choose = false;

    public WardListBean(){

    }

    public WardListBean(Parcel in) {
        deviceId = in.readInt();
        duration = in.readInt();
        end = in.readString();
        roomCode = in.readString();
        status = in.readString();
        choose = in.readByte() != 0;
    }

    public static final Creator<WardListBean> CREATOR = new Creator<WardListBean>() {
        @Override
        public WardListBean createFromParcel(Parcel in) {
            return new WardListBean(in);
        }

        @Override
        public WardListBean[] newArray(int size) {
            return new WardListBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(deviceId);
        dest.writeInt(duration);
        dest.writeString(end);
        dest.writeString(roomCode);
        dest.writeString(status);
        dest.writeByte((byte) (choose ? 1 : 0));
    }
}
