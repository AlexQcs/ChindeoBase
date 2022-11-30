package com.chindeo.repository.data.model.nurses;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Objects;

public class NursesEventTags implements Parcelable {


    @JSONField(name = "code")
    public String code;
    @JSONField(name = "color")
    public String color;
    @JSONField(name = "iconDisplay")
    public int iconDisplay;
    @JSONField(name = "icon")
    public String icon;
    @JSONField(name = "id")
    public int id;
    @JSONField(name = "sort")
    public int sort;
    @JSONField(name = "desc")
    public String desc;

    public NursesEventTags() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NursesEventTags that = (NursesEventTags) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    protected NursesEventTags(Parcel in) {
        code = in.readString();
        color = in.readString();
        iconDisplay = in.readInt();
        icon = in.readString();
        id = in.readInt();
        sort = in.readInt();
        desc = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(color);
        dest.writeInt(iconDisplay);
        dest.writeString(icon);
        dest.writeInt(id);
        dest.writeInt(sort);
        dest.writeString(desc);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NursesEventTags> CREATOR = new Creator<NursesEventTags>() {
        @Override
        public NursesEventTags createFromParcel(Parcel in) {
            return new NursesEventTags(in);
        }

        @Override
        public NursesEventTags[] newArray(int size) {
            return new NursesEventTags[size];
        }
    };

    public static NursesEventTags all(){
        NursesEventTags all = new NursesEventTags();
        all.desc = "全部";
        all.sort = -9999;
        all.id = -99;
        return all;
    }
}
