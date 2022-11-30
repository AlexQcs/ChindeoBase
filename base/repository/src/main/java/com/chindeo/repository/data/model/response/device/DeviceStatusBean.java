package com.chindeo.repository.data.model.response.device;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

public class DeviceStatusBean {

    @JSONField(name = "offline")
    public List<StatusBean> offline;
    @JSONField(name = "total")
    public List<StatusBean> total;
    @JSONField(name = "abnormal")
    public List<StatusBean> abnormal;
    @JSONField(name = "online")
    public List<StatusBean> online;
    @JSONField(name = "name")
    public String name; // 本地赋值
    @JSONField(name = "deviceType")
    public int deviceType; // 本地赋值

    public List<StatusBean>  getTotalList(){
        if (total==null){
            return new ArrayList<>();
        }
        return total;
    }
    public List<StatusBean>  getOffList(){
        if (offline==null){
            return new ArrayList<>();
        }
        return offline;
    }
    public List<StatusBean>  getOnlineList(){
        if (online==null){
            return new ArrayList<>();
        }
        return online;
    }

    public static class StatusBean {
        @JSONField(name = "id")
        public int id;
        @JSONField(name = "code")
        public String code;
        @JSONField(name = "desc")
        public String desc;
        @JSONField(name = "type")
        public String type;
        @JSONField(name = "status")
        public String status;
        @JSONField(name = "position")
        public String position;
        @JSONField(name = "volume")
        public int volume; // 本地数据

        public String getStatusDesc(){
            switch (status){
                case "1":
                    return "在线";
                case "0":
                    return "离线";
                default:
                    return "异常";
            }
        }
    }

}
