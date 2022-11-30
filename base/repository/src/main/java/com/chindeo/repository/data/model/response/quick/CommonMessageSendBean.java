package com.chindeo.repository.data.model.response.quick;

//所有消息的头部封装
public class CommonMessageSendBean {

    private String fromUser;// "1413", //发送者，患者就诊号，病区编码
    private String toUser;// "9279226", //接收者，患者就诊号，病区编码
    private String msgType;// "text", //文本，默认
    private QuickMsgBean msg;// "" //消息
    private String sendType;// 1, // 发送类型，1单聊，默认
    private String sendUserType;//"1", //发送消息端,1:患者端（床头屏），2:护士端(护士站主机，护理白板)
    private String category;// "call", // 消息类型，快速呼叫

    public String getSendUserType() {
        return sendUserType;
    }

    public void setSendUserType(String sendUserType) {
        this.sendUserType = sendUserType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public QuickMsgBean getMsg() {
        return msg;
    }

    public void setMsg(QuickMsgBean msg) {
        this.msg = msg;
    }


}
