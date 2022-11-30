package com.chindeo.repository.data.model.nurses;

import com.alibaba.fastjson.annotation.JSONField;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.lazylibs.util.TimeUtils;

public class ChatBean implements MultiItemEntity {

    @JSONField(name = "id")
    public String msgId;
    @JSONField(name = "createTime")
    public String createTime;
    @JSONField(name = "content")
    public String content;
    @JSONField(name = "sendUserType")
    public int sendUserType; // 1 患者 2 护士站
    @JSONField(name = "read")
    public int read;
    @JSONField(name="status")
    public MsgStatusEnum status;
    @JSONField(name="sendType")//发送人类型，0:系统，1:患者端，2:护士病区端，3:房间端
    public String sendType;
    @JSONField(name="type") //消息类别,0：文本；1：图片：2：视频，3：音频，4：文档
    public String type;
    @JSONField(name="msgNo") //接受编号
    public String msgNo;
    @JSONField(name="title")
    public String title;

    @JSONField(name = "isMe")
    public boolean isRightMessage; // 左边显示还是右边的消息

    public ChatBean() {
    }


    public ChatBean(String msgId, String createTime, String content, int sendUserType) {
        this.msgId = msgId;
        this.createTime = createTime;
        this.content = content;
        this.sendUserType = sendUserType;
    }

    public static ChatBean item(String msgId, String createTime, String content, boolean isMySend) {
        ChatBean bean = new ChatBean();
        bean.msgId = msgId;
        bean.createTime = createTime;
        bean.content = content;
        bean.isRightMessage = isMySend;
        bean.status = MsgStatusEnum.success;
        return bean;
    }

    public static ChatBean sending(String id, String createTime, String content, boolean isMySend) {
        ChatBean bean = new ChatBean();
        bean.msgId = id;
        bean.createTime = createTime;
        bean.content = content;
        bean.isRightMessage = isMySend;
        bean.status = MsgStatusEnum.sending;
        return bean;
    }

    public long getTime(){
        return TimeUtils.string2Millis(createTime);
    }

    @Override
    public int getItemType() {
        return isRightMessage  ? 1 : 0;
    }
}
