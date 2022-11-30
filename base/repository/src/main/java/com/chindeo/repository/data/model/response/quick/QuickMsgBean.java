package com.chindeo.repository.data.model.response.quick;

//聊天的msg对象
public class QuickMsgBean {
    private String read;// "1",
    private String createTime;// "2019-09-05 10:58:42",
    private String sendType;// "1",1, // 发送类型，1单聊，默认
    private String id;// 6111,
    private String type;// "0", //0文本
    private String title;// "17床",
    private String number;// "音视频号码",
    private String content;// "疼痛护理",
    private String msgNo;// "" //消息编号

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private String reason;// "" //呼叫类型


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMsgNo() {
        return msgNo;
    }

    public void setMsgNo(String msgNo) {
        this.msgNo = msgNo;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
