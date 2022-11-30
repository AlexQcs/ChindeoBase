package com.chindeo.repository.data.model.call;

import com.alibaba.fastjson.annotation.JSONField;
import com.chindeo.repository.contants.RtcConstants;

import java.util.Objects;

/**
 * 未接电话的bean
 */
public class CallLogBean {


    @JSONField(name = "number")
    public String number;
    @JSONField(name = "remoteUsername")
    public String remoteUsername;
    @JSONField(name = "startTime")
    public long startTime= System.currentTimeMillis();
    @JSONField(name = "isReply")
    public boolean isReply=false;
    @JSONField(name = "isVideoCall")
    public boolean isVideoCall;
    @JSONField(name = "type")
    public int type; //

    public int count=1;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String typeDesc(){
        return RtcConstants.getRtc(type).desc;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemoteUsername() {
        return remoteUsername;
    }

    public void setRemoteUsername(String remoteUsername) {
        this.remoteUsername = remoteUsername;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }

    public boolean isVideoCall() {
        return isVideoCall;
    }

    public void setVideoCall(boolean videoCall) {
        isVideoCall = videoCall;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallLogBean bean = (CallLogBean) o;
        return startTime == bean.startTime && isReply == bean.isReply && isVideoCall == bean.isVideoCall && type == bean.type && count == bean.count && Objects.equals(number, bean.number) && Objects.equals(remoteUsername, bean.remoteUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, remoteUsername, startTime, isReply, isVideoCall, type, count);
    }

    @Override
    public String toString() {
        return "CallLogBean{" +
                "number='" + number + '\'' +
                ", remoteUsername='" + remoteUsername + '\'' +
                ", startTime=" + startTime +
                ", isReply=" + isReply +
                ", isVideoCall=" + isVideoCall +
                ", type=" + type +
                ", count=" + count +
                '}';
    }
}
