package com.chindeo.repository.data.model.response.quick;

// 查询快速呼叫的历史记录
public class QuickCallContentBean {

    private int read;//"0",
    private String createTime;//"2019-08-06 16:18:00",
    private int sendUserType;//"1是患者，2是大屏 or 护士站",
    private int sendType;//0群聊，"1是单聊",
    private String id;//1679,
    private String msgNo;
    private int type;//"0 是文本
    private String title;//"17床",
    private String content;//"我是患者"
    private String category;//"call"
    private String formUser;//"11588880"
    private String toUser;//"1413"

    public QuickCallContentBean(String createTime, int sendUserType, String content) {
        this.createTime = createTime;
        this.sendUserType = sendUserType;
        this.content = content;
    }

    public String getFormUser() {
        return formUser;
    }

    public void setFormUser(String formUser) {
        this.formUser = formUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }


    public int getSendUserType() {
        return sendUserType;
    }

    public void setSendUserType(int sendUserType) {
        this.sendUserType = sendUserType;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getSendType() {
        return sendType;
    }

    public void setSendType(int sendType) {
        this.sendType = sendType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
