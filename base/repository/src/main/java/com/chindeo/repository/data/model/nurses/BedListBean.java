package com.chindeo.repository.data.model.nurses;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Objects;

public class BedListBean implements Parcelable {


    @JSONField(name = "id")
    public String id;
    @JSONField(name = "code")
    public String code; // 床号
    @JSONField(name = "locId")
    public String locId;
    @JSONField(name = "room")
    public String room;
    @JSONField(name = "status")
    public int status; // 0离线 1在线 2异常
    @JSONField(name = "sort")
    public int sort;
    @JSONField(name = "reserved")
    public boolean reserved; //是否 是 留床  0否 1是
    @JSONField(name = "urgent")
    public boolean urgent; // 应急
    @JSONField(name = "observation")
    public boolean observation; //留观字段
    @JSONField(name = "unavailable")
    public int unavailable;
    @JSONField(name = "deviceUser")
    public DeviceUserBean deviceUser;
    @JSONField(name = "point")
    public Object point;
    @JSONField(name = "patient")
    public PatientBean patient;

    public String formatBed() {
        return code.replace("床", "") + "床";
    }

    protected BedListBean(Parcel in) {
        id = in.readString();
        code = in.readString();
        locId = in.readString();
        room = in.readString();
        sort = in.readInt();
        reserved = in.readByte() != 0;
        urgent = in.readByte() != 0;
        observation = in.readByte() != 0;
        unavailable = in.readInt();
        deviceUser = in.readParcelable(DeviceUserBean.class.getClassLoader());
        patient = in.readParcelable(PatientBean.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(code);
        dest.writeString(locId);
        dest.writeString(room);
        dest.writeInt(sort);
        dest.writeByte((byte) (reserved ? 1 : 0));
        dest.writeByte((byte) (urgent ? 1 : 0));
        dest.writeByte((byte) (observation ? 1 : 0));
        dest.writeInt(unavailable);
        dest.writeParcelable(deviceUser, flags);
        dest.writeParcelable(patient, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BedListBean> CREATOR = new Creator<BedListBean>() {
        @Override
        public BedListBean createFromParcel(Parcel in) {
            return new BedListBean(in);
        }

        @Override
        public BedListBean[] newArray(int size) {
            return new BedListBean[size];
        }
    };

    public boolean isUrgent() {
        return urgent;
    }

    public boolean isReserved() {
        return reserved;
    }

    public boolean isObservation() {
        return observation;
    }

    public BedListBean() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BedListBean that = (BedListBean) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(locId, that.locId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, locId);
    }


}
