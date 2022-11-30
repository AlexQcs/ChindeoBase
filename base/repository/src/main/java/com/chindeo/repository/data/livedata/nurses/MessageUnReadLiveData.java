package com.chindeo.repository.data.livedata.nurses;

import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.apkfuns.logutils.LogUtils;
import com.chindeo.repository.data.model.nurses.MessageUnReadBean;
import com.chindeo.repository.mmkv.impl.NursesCache;
import com.chindeo.repository.util.LoggerUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public enum MessageUnReadLiveData {

    INSTANCE;

    private MutableLiveData<List<MessageUnReadBean>> liveData;

    MessageUnReadLiveData() {
    }


    public MutableLiveData<List<MessageUnReadBean>> getLiveData() {
        if (liveData == null) {
            liveData = new MutableLiveData<List<MessageUnReadBean>>() {
                @Nullable
                @Override
                public List<MessageUnReadBean> getValue() {
                    return super.getValue() == null ? new ArrayList<>() : super.getValue();
                }

                @Override
                public void postValue(List<MessageUnReadBean> value) {
                    super.postValue(value);
                    NursesCache.addMessageUnReadIds(NursesMainDataLiveData.getMqttLocCode(), value);
                }
            };
        }
        return liveData;
    }

    public static int getUnRead(String admNo) {
        return getItem(admNo).msgIds.size();
    }

    public static void addUnRead(String admNo, String msgId) {
        List<MessageUnReadBean> list = MessageUnReadLiveData.INSTANCE.getLiveData().getValue();
        MessageUnReadBean bean = getItem(admNo);
        if (bean.msgIds.contains(msgId)) {
            return;
        }
        LogUtils.d("添加未读消息 admNo:" + admNo + ", msgId:" + msgId);
        if (list.contains(bean)) {
            bean.msgIds.add(msgId);
        } else {
            list = addMsgToList(admNo, msgId);
        }
        MessageUnReadLiveData.INSTANCE.getLiveData().postValue(list);
    }

    private static List<MessageUnReadBean> addMsgToList(String admNo, String msgId) {
        List<MessageUnReadBean> list = MessageUnReadLiveData.INSTANCE.getLiveData().getValue();
        MessageUnReadBean item = getItem(admNo);
        item.msgIds.add(msgId);
        if (!list.contains(item)) {
            list.add(item);
        }
        return list;
    }

    public static void clearMsgCount(String admNo) {
        List<MessageUnReadBean> list = MessageUnReadLiveData.INSTANCE.getLiveData().getValue();
        list.remove(getItem(admNo));
        MessageUnReadLiveData.INSTANCE.getLiveData().postValue(list);
    }

    private static MessageUnReadBean getItem(String admNo) {
        List<MessageUnReadBean> list = MessageUnReadLiveData.INSTANCE.getLiveData().getValue();
        for (MessageUnReadBean messageUnReadBean : list) {
            if (TextUtils.isEmpty(messageUnReadBean.admNo)) {
                continue;
            }
            if (messageUnReadBean.admNo.equals(admNo)) {
                return messageUnReadBean;
            }
        }

        return new MessageUnReadBean(admNo);
    }

    public static List<MessageUnReadBean> init(String locCode) {
        List<MessageUnReadBean> unReadList = NursesCache.getMessageUnReadIds(locCode);
        List<MessageUnReadBean> removeList = new ArrayList<>();
        Iterator<MessageUnReadBean> it = unReadList.iterator();
        while (it.hasNext()){
            MessageUnReadBean unReadBean = it.next();
            if (TextUtils.isEmpty(unReadBean.admNo)){
                removeList.add(unReadBean);
            }
        }
        for (MessageUnReadBean messageUnReadBean : removeList) {
            unReadList.remove(messageUnReadBean);
        }
        INSTANCE.getLiveData().postValue(unReadList);
        Log.v(MessageUnReadLiveData.class.getName(), "未读数量" + unReadList.size());
        return unReadList;
    }

    public static List<MessageUnReadBean> list(String locCode) {
        List<MessageUnReadBean> unReadList = NursesCache.getMessageUnReadIds(locCode);
        return unReadList;
    }

}
