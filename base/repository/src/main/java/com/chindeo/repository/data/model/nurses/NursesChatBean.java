package com.chindeo.repository.data.model.nurses;

public class NursesChatBean extends ChatBean {

    public static NursesChatBean item(String msgId, String createTime, String content, boolean isMySend) {
        NursesChatBean bean = new NursesChatBean();
        bean.msgId = msgId;
        bean.createTime = createTime;
        bean.content = content;
        bean.isRightMessage = isMySend;
        bean.status = MsgStatusEnum.success;
        return bean;
    }

    public static NursesChatBean sending(String id, String createTime, String content, boolean isMySend) {
        NursesChatBean bean = new NursesChatBean();
        bean.msgId = id;
        bean.createTime = createTime;
        bean.content = content;
        bean.isRightMessage = isMySend;
        bean.status = MsgStatusEnum.sending;
        return bean;
    }

    @Override
    public int getItemType() {
        if (isRightMessage || "2".equals(sendUserType))
            return 1;
        else
            return 0;
    }
}
