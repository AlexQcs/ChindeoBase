package com.chindeo.repository.data.model.response.upgrade;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.R;

import java.util.Objects;

public class PluginBean implements Parcelable {


    @JSONField(name = "name")
    public String name;
    @JSONField(name = "hash")
    public String hash;
    @JSONField(name = "version")
    public String version;
    @JSONField(name = "package")
    public String packageName;
    @JSONField(name = "status")
    public Status status = Status.UN_KNOW;
    @JSONField(name = "progress")
    public String downloadProgress;

    public PluginBean() {
    }

    protected PluginBean(Parcel in) {
        name = in.readString();
        hash = in.readString();
        version = in.readString();
        packageName = in.readString();
        downloadProgress = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hash);
        dest.writeString(version);
        dest.writeString(packageName);
        dest.writeString(downloadProgress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PluginBean> CREATOR = new Creator<PluginBean>() {
        @Override
        public PluginBean createFromParcel(Parcel in) {
            return new PluginBean(in);
        }

        @Override
        public PluginBean[] newArray(int size) {
            return new PluginBean[size];
        }
    };

    @Override
    public String toString() {
        return "PluginBean{" +
                "name='" + name + '\'' +
                ", hash='" + hash + '\'' +
                ", version='" + version + '\'' +
                ", package='" + packageName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PluginBean that = (PluginBean) o;
        return Objects.equals(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageName);
    }

    public enum Status {
        UN_KNOW(R.string.status_un_know),
        UN_INSTALL(R.string.status_un_install),
        INSTALL(R.string.status_installed),
        DOWNLOADING(R.string.status_downloading),
        DOWNLOAD_FINISH(R.string.status_download_finish),
        TEST(R.string.status_test),
        ;

        public final int resId;

        Status(int resId) {
            this.resId = resId;
        }

        public String getStr(Context context){
            return context.getString(resId);
        }
    }
}
