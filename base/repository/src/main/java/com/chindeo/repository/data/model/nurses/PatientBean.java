package com.chindeo.repository.data.model.nurses;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;
import java.util.Objects;

//患者实体
public class PatientBean implements Parcelable {

    @JSONField(name = "id")
    public String id;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "sex")
    public String sex;
    @JSONField(name = "admNo")
    public String admNo;
    @JSONField(name = "patNo")
    public String patNo;
    @JSONField(name = "locDesc")
    public String locDesc;
    @JSONField(name = "bedDate")
    public String bedDate;
    @JSONField(name = "doctor")
    public String doctor;
    @JSONField(name = "nurse")
    public String nurse;
    @JSONField(name = "bedDay")
    public int bedDay;
    @JSONField(name = "age")
    public String age;
    @JSONField(name = "diaDesc")
    public Object diaDesc;
    @JSONField(name = "diaShow")
    public int diaShow;
    @JSONField(name = "nurseLevel")
    public int nurseLevel;
    @JSONField(name = "allergyList")
    public Object allergyList;
    @JSONField(name = "bedCode")
    public String bedCode; // 本地赋值
    @JSONField(name = "isUnRead")
    public boolean messageUnRead; // 本地赋值
    @JSONField(name = "nurseEventTags")
    public List<NursesEventTags> nurseEventTags;
    @JSONField(name = "admConfig")
    public PatientAuthorityBean admConfig;
    @JSONField(name = "admState") //患者诊疗状态
    public String admState;

    public String formatBed() {
        return bedCode.replace("床", "") + "床";
    }

    public PatientBean() {
    }

    protected PatientBean(Parcel in) {
        id = in.readString();
        name = in.readString();
        sex = in.readString();
        admNo = in.readString();
        patNo = in.readString();
        locDesc = in.readString();
        bedDate = in.readString();
        doctor = in.readString();
        nurse = in.readString();
        bedDay = in.readInt();
        age = in.readString();
        diaShow = in.readInt();
        nurseLevel = in.readInt();
        bedCode = in.readString();
        messageUnRead = in.readByte() != 0;
        nurseEventTags = in.createTypedArrayList(NursesEventTags.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeString(admNo);
        dest.writeString(patNo);
        dest.writeString(locDesc);
        dest.writeString(bedDate);
        dest.writeString(doctor);
        dest.writeString(nurse);
        dest.writeInt(bedDay);
        dest.writeString(age);
        dest.writeInt(diaShow);
        dest.writeInt(nurseLevel);
        dest.writeString(bedCode);
        dest.writeByte((byte) (messageUnRead ? 1 : 0));
        dest.writeTypedList(nurseEventTags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PatientBean> CREATOR = new Creator<PatientBean>() {
        @Override
        public PatientBean createFromParcel(Parcel in) {
            return new PatientBean(in);
        }

        @Override
        public PatientBean[] newArray(int size) {
            return new PatientBean[size];
        }
    };

    public String hasNursesLevelEventName() {
        if (nurseEventTags != null) {
            for (NursesEventTags nurseEventTag : nurseEventTags) {
                if (nurseEventTag.desc.contains("护理")) {
                    return nurseEventTag.desc.substring(0, nurseEventTag.desc.indexOf("护理"));
                }
            }
        }
        return null;
    }

    public NursesEventTags getNursesLevelEvent() {
        if (nurseEventTags != null) {
            for (NursesEventTags nurseEventTag : nurseEventTags) {
                if (nurseEventTag.desc.contains("护理")) {
                    return nurseEventTag;
                }
            }
        }
        return null;
    }

    public static PatientBean empty(String bedId, String code) {
        PatientBean bean = new PatientBean();
        bean.name = code.replace("床", "") + "床";
        bean.id = bedId;
        return bean;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientBean that = (PatientBean) o;
        if (that.admNo != null && admNo != null) {
            return Objects.equals(admNo, that.admNo);
        } else {
            return Objects.equals(id, that.id);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(admNo);
    }

}
