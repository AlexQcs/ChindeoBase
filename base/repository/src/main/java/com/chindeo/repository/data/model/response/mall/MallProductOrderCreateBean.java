package com.chindeo.repository.data.model.response.mall;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

public class MallProductOrderCreateBean implements Parcelable {

    @JSONField(name = "id")
    public String id;

    @JSONField(name = "qrcode")
    public String qrcode;

    public MallProductOrderCreateBean() {
    }

    protected MallProductOrderCreateBean(Parcel in) {
        id = in.readString();
        qrcode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(qrcode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MallProductOrderCreateBean> CREATOR = new Creator<MallProductOrderCreateBean>() {
        @Override
        public MallProductOrderCreateBean createFromParcel(Parcel in) {
            return new MallProductOrderCreateBean(in);
        }

        @Override
        public MallProductOrderCreateBean[] newArray(int size) {
            return new MallProductOrderCreateBean[size];
        }
    };
}
