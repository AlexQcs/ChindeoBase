package com.chindeo.repository.data.model.response.arcitem;

import com.alibaba.fastjson.annotation.JSONField;


public class PatientInfo {

    @JSONField(name = "diag")
    public String diag;
    @JSONField(name = "nation")
    public String nation;
    @JSONField(name = "patId")
    public int patId;
    @JSONField(name = "locName")
    public String locName;
    @JSONField(name = "foreignPhone")
    public String foreignPhone;
    @JSONField(name = "admNo")
    public String admNo;
    @JSONField(name = "total")
    public String total;
    @JSONField(name = "bedDate")
    public String bedDate;
    @JSONField(name = "bedCode")
    public String bedCode;
    @JSONField(name = "foreignName")
    public String foreignName;
    @JSONField(name = "balance")
    public String balance;
    @JSONField(name = "nurse")
    public String nurse;
    @JSONField(name = "admReason")
    public String admReason;
    @JSONField(name = "locId")
    public int locId;
    @JSONField(name = "admId")
    public int admId;
    @JSONField(name = "bedDay")
    public int bedDay;
    @JSONField(name = "sex")
    public String sex;
    @JSONField(name = "bedId")
    public String bedId;
    @JSONField(name = "birth")
    public String birth;
    @JSONField(name = "doctor")
    public String doctor;
    @JSONField(name = "visitStatus")
    public String visitStatus;
    @JSONField(name = "inNo")
    public String inNo;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "deposit")
    public String deposit;
    @JSONField(name = "allergyNo")
    public int allergyNo;
}
