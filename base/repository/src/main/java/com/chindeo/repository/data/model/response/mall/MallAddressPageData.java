package com.chindeo.repository.data.model.response.mall;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MallAddressPageData {


    @JSONField(name = "list")
    public List<ListBean> list;
    @JSONField(name = "total")
    public int total;
    @JSONField(name = "page")
    public int page;
    @JSONField(name = "pageSize")
    public int pageSize;

    public static class ListBean implements Parcelable {

        @JSONField(name = "id")
        public int id;
        @JSONField(name = "createdAt")
        public String createdAt;
        @JSONField(name = "updatedAt")
        public String updatedAt;
        @JSONField(name = "name")
        public String name;
        @JSONField(name = "phone")
        public String phone;
        @JSONField(name = "sex")
        public int sex;
        @JSONField(name = "country")
        public String country;
        @JSONField(name = "province")
        public String province;
        @JSONField(name = "city")
        public String city;
        @JSONField(name = "district")
        public String district;
        @JSONField(name = "isDefault")
        public int isDefault;
        @JSONField(name = "detail")
        public String detail;
        @JSONField(name = "postcode")
        public String postcode;
        @JSONField(name = "age")
        public int age;
        @JSONField(name = "hospitalName")
        public String hospitalName;
        @JSONField(name = "locName")
        public String locName;
        @JSONField(name = "bedNum")
        public String bedNum;
        @JSONField(name = "roomNum")
        public String roomNum;
        @JSONField(name = "hospitalNo")
        public String hospitalNo;
        @JSONField(name = "disease")
        public String disease;
        @JSONField(name = "cUserId")
        public int cUserId;

        public ListBean() {
        }

        protected ListBean(Parcel in) {
            id = in.readInt();
            createdAt = in.readString();
            updatedAt = in.readString();
            name = in.readString();
            phone = in.readString();
            sex = in.readInt();
            country = in.readString();
            province = in.readString();
            city = in.readString();
            district = in.readString();
            isDefault = in.readInt();
            detail = in.readString();
            postcode = in.readString();
            age = in.readInt();
            hospitalName = in.readString();
            locName = in.readString();
            bedNum = in.readString();
            roomNum = in.readString();
            hospitalNo = in.readString();
            disease = in.readString();
            cUserId = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(createdAt);
            dest.writeString(updatedAt);
            dest.writeString(name);
            dest.writeString(phone);
            dest.writeInt(sex);
            dest.writeString(country);
            dest.writeString(province);
            dest.writeString(city);
            dest.writeString(district);
            dest.writeInt(isDefault);
            dest.writeString(detail);
            dest.writeString(postcode);
            dest.writeInt(age);
            dest.writeString(hospitalName);
            dest.writeString(locName);
            dest.writeString(bedNum);
            dest.writeString(roomNum);
            dest.writeString(hospitalNo);
            dest.writeString(disease);
            dest.writeInt(cUserId);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel in) {
                return new ListBean(in);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }
}
