package com.chindeo.repository.data.model.params.mall;

import com.alibaba.fastjson.annotation.JSONField;

public class MallLoginParams {

    @JSONField(name = "uuid")
    public String uuid;
    @JSONField(name = "name")
    public String name;
    @JSONField(name = "phone")
    public String phone;
    @JSONField(name = "room_num")
    public String roomNum;
    @JSONField(name = "sex")
    public int sex; //1男2女
    @JSONField(name = "locName")
    public String locName;
    @JSONField(name = "bedNum")
    public String bedNum;
    @JSONField(name = "hospitalNo")
    public String hospitalNo;
    @JSONField(name = "disease")
    public String disease;

    public MallLoginParams(String uuid, String name, String phone, String roomNum, int sex, String locName, String bedNum, String hospitalNo, String disease) {
        this.uuid = uuid;
        this.name = name;
        this.phone = phone;
        this.roomNum = roomNum;
        this.sex = sex;
        this.locName = locName;
        this.bedNum = bedNum;
        this.hospitalNo = hospitalNo;
        this.disease = disease;
    }

    public MallLoginParams() {
    }
}
