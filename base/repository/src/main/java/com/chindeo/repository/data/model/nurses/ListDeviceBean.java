package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class ListDeviceBean {


    @JSONField(name = "loc")
    public LocBean loc;
    @JSONField(name = "ftp")
    public FtpBean ftp;
    @JSONField(name = "chat")
    public List<String> chat;
    @JSONField(name = "locConfig")
    public PatientAuthorityBean locConfig;

    @JSONField(name = "bedList")
    public List<BedListBean> bedList;

}
