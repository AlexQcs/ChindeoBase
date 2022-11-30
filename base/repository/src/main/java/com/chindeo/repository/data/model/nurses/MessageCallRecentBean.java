package com.chindeo.repository.data.model.nurses;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.alibaba.fastjson.annotation.JSONField;
import com.lazylibs.util.TimeUtils;

import java.util.Objects;

public class MessageCallRecentBean implements Parcelable {

    @JSONField(name = "admId")
    public String admId;
    @JSONField(name = "bedCode")
    public String bedCode;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "time")
    public String time;
    @JSONField(name = "admNo")
    public String admNo;
    @JSONField(name = "content")
    public String content;

    @JSONField(name = "unRead")
    public int unRead; // 本地记录


    public String formatBed() {
        return bedCode.replace("床", "") + "床";
    }

    public MessageCallRecentBean(String admNo, String time, String content) {
        this.admNo = admNo;
        this.time = time;
        this.content = content;
        unRead = 1;
    }

    public MessageCallRecentBean(String admNo) {
        this.admNo = admNo;
        time = TimeUtils.millis2String(System.currentTimeMillis());
    }

    private MessageCallRecentBean() {
    }

    public static MessageCallRecentBean create(String admNo, String bedCode, String name) {
        MessageCallRecentBean bean = new MessageCallRecentBean();
        bean.admNo = admNo;
        bean.bedCode = bedCode;
        bean.name = name;
        bean.time = TimeUtils.millis2String(System.currentTimeMillis()); // 用来获取最近聊天记录
        Log.v(MessageCallRecentBean.class.getName(), "创建新实例 MessageCallRecentBean -> " + bean.toString());
        return bean;
    }

    protected MessageCallRecentBean(Parcel in) {
        admId = in.readString();
        bedCode = in.readString();
        name = in.readString();
        time = in.readString();
        admNo = in.readString();
        content = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(admId);
        dest.writeString(bedCode);
        dest.writeString(name);
        dest.writeString(time);
        dest.writeString(admNo);
        dest.writeString(content);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MessageCallRecentBean> CREATOR = new Creator<MessageCallRecentBean>() {
        @Override
        public MessageCallRecentBean createFromParcel(Parcel in) {
            return new MessageCallRecentBean(in);
        }

        @Override
        public MessageCallRecentBean[] newArray(int size) {
            return new MessageCallRecentBean[size];
        }
    };


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageCallRecentBean that = (MessageCallRecentBean) o;
        return Objects.equals(admNo, that.admNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admNo);
    }

    @Override
    public String toString() {
        return "MessageCallRecentBean{" +
                "admId='" + admId + '\'' +
                ", bedCode='" + bedCode + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", admNo='" + admNo + '\'' +
                ", content='" + content + '\'' +
                ", unRead=" + unRead +
                '}';
    }
}
