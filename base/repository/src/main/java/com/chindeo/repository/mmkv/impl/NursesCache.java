package com.chindeo.repository.mmkv.impl;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.chindeo.repository.data.model.nurses.MessageUnReadBean;
import com.chindeo.repository.data.model.nurses.MissedRecordReturnBean;
import com.chindeo.repository.data.model.nurses.ZlpVolumeSettingBean;
import com.chindeo.repository.mmkv.ICache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class NursesCache {

    private static ICache getCache() {
        return ICache.create("nursesApp");
    }

    private static final String KEY_LAST_CLEAR_CACHE_TIME = "last_clear_time"; // 最后清数据时间
    private static final String KEY_MISSED_RECORD = "missed_record_return_ids"; // 回拨记录
    private static final String KEY_MESSAGE_UNREAD_LIST = "message_unread_list"; // 聊天未读列表
    private static final String KEY_DOCTOR_UNREAD_LIST = "doctor_unread_list"; // 医生录音未读列表 // todo 没有清数据 数据会一直累加。
    private static final String KEY_ZLP_VOLUME_LIST = "zlp_volume_list"; // 走廊屏音量
    private static final String KEY_BROADCAST_TASK_LIST = "broadcast_task_list_test14"; // 定时广播任务

    public static List<MissedRecordReturnBean> missedRecordAdd(MissedRecordReturnBean newRecord) {
        String json = getCache().getString(KEY_MISSED_RECORD);
        List<MissedRecordReturnBean> ids;
        if (TextUtils.isEmpty(json)) {
            ids = new ArrayList<>();
        } else {
            ids = JSON.parseObject(json, new TypeReference<ArrayList<MissedRecordReturnBean>>() {
            });
        }

        boolean hasRecord = false;
        for (MissedRecordReturnBean id : ids) {
            if (id.id.equals(newRecord.id)) {
                hasRecord = true;
                id.time = newRecord.time;
            }
        }
        if (!hasRecord) {
            ids.add(newRecord);
        }
        getCache().put(KEY_MISSED_RECORD, JSON.toJSONString(ids));
        return ids;
    }

    public static List<MissedRecordReturnBean> missedRecordHasReturnList() {
        String json = getCache().getString(KEY_MISSED_RECORD);
        List<MissedRecordReturnBean> ids;
        if (TextUtils.isEmpty(json)) {
            ids = new ArrayList<>();
        } else {
            ids = JSON.parseObject(json, new TypeReference<List<MissedRecordReturnBean>>() {
            });
        }
        HashSet<MissedRecordReturnBean> set = new HashSet<>(ids);
        //把List集合所有元素清空
        ids.clear();
        //把HashSet对象添加至List集合
        ids.addAll(set);
        return ids;
    }

    public static List<MessageUnReadBean> getMessageUnReadIds(String locCode) {
        String json = getCache().getString(KEY_MESSAGE_UNREAD_LIST + locCode);
        if (TextUtils.isEmpty(json)) {
            return new LinkedList<>();
        }
        return JSON.parseObject(json, new TypeReference<LinkedList<MessageUnReadBean>>() {
        });
    }
//
//    public static void addUnReadId(MessageUnReadBean msgId) {
//        List<MessageUnReadBean> list = getMessageUnReadIds();
//        if (!list.isEmpty()) {
//            if (list.contains(msgId)) {
//                return;
//            }
//            list.add(msgId);
//            HashSet<MessageUnReadBean> set = new HashSet<>(list.size());
//            set.addAll(list);
//            list.clear();
//            list.addAll(set);
//        } else {
//            list.add(msgId);
//        }
//        getCache().put(KEY_UNREAD_LIST, JSON.toJSONString(list));
//    }

    public static void addMessageUnReadIds(String locCode, List<MessageUnReadBean> ids) {
        List<MessageUnReadBean> list = getMessageUnReadIds(locCode);
        List<MessageUnReadBean> result = new ArrayList<>();
        for (MessageUnReadBean temp : list) {
            if (ids.contains(temp)) {
                result.add(temp);
            }
        }
        if (!result.isEmpty()) {
            HashSet<MessageUnReadBean> set = new HashSet<>(result.size());
            set.addAll(result);
            set.addAll(ids);
            result.clear();
            result.addAll(set);
            getCache().put(KEY_MESSAGE_UNREAD_LIST + locCode, JSON.toJSONString(result));
        } else {
            getCache().put(KEY_MESSAGE_UNREAD_LIST + locCode, JSON.toJSONString(ids));
        }
    }

    public static List<String> getDoctorReadIds() {
        String json = getCache().getString(KEY_DOCTOR_UNREAD_LIST);
        if (TextUtils.isEmpty(json)) {
            return new LinkedList<>();
        }
        return JSON.parseObject(json, new TypeReference<List<String>>() {
        });
    }

    public static List<String> addDoctorVoiceReadId(String id) {
        List<String> ids = getDoctorReadIds();
        if (ids.contains(id)) {
            return ids;
        }
        ids.add(id);
        getCache().put(KEY_DOCTOR_UNREAD_LIST, JSON.toJSONString(ids));
        return ids;
    }

    public static List<ZlpVolumeSettingBean> getZlpVolumeSetting() {
        String json = getCache().getString(KEY_ZLP_VOLUME_LIST);
        if (TextUtils.isEmpty(json)) {
            return new ArrayList<>();
        }
        return JSON.parseObject(json, new TypeReference<List<ZlpVolumeSettingBean>>() {
        });
    }

    public static List<ZlpVolumeSettingBean> addZlpVolumeSetting(String deviceCode, int volume) {
        List<ZlpVolumeSettingBean> list = getZlpVolumeSetting();
        ZlpVolumeSettingBean bean = new ZlpVolumeSettingBean(deviceCode, volume);
        if (list.contains(bean)) {
            for (ZlpVolumeSettingBean zlpVolumeSettingBean : list) {
                if (zlpVolumeSettingBean.equals(bean)) {
                    zlpVolumeSettingBean.volume = bean.volume;
                }
            }
        } else {
            list.add(bean);
        }
        saveZlpVolumeSettingCache(list);
        return list;
    }

    private static void saveZlpVolumeSettingCache(List<ZlpVolumeSettingBean> list) {
        getCache().put(KEY_ZLP_VOLUME_LIST, JSON.toJSONString(list));
    }

//    public static List<TimingTaskListBean> getTimingTaskList() {
//        return getTimingTaskList(null);
//    }

//    public static List<TimingTaskListBean> getTimingTaskList(String attach) {
//        String json = getCache().getString(KEY_BROADCAST_TASK_LIST);
//        if (TextUtils.isEmpty(json)) {
//            return new ArrayList<>();
//        }
//        List<TimingTaskListBean> list = JSON.parseObject(json, new TypeReference<List<TimingTaskListBean>>() {
//        });
//        if (attach == null) {
//            Collections.sort(list, new Comparator<TimingTaskListBean>() {
//                @Override
//                public int compare(TimingTaskListBean taskBean, TimingTaskListBean t1) {
//                    return Long.compare(taskBean.getTask().getTaskTimeStamp(), t1.getTask().getTaskTimeStamp());
//                }
//            });
//            return list;
//        } else {
//            List<TimingTaskListBean> filterList = new ArrayList<>();
//            for (TimingTaskListBean item : list) {
//                if (item.getAttachTab().equals(attach)) {
//                    filterList.add(item);
//                }
//            }
//            Collections.sort(filterList, new Comparator<TimingTaskListBean>() {
//                @Override
//                public int compare(TimingTaskListBean taskBean, TimingTaskListBean t1) {
//                    return Long.compare(taskBean.getTask().getTaskTimeStamp(), t1.getTask().getTaskTimeStamp());
//                }
//            });
//            return filterList;
//        }
//    }

    /**
     * 检查是否有效
     *
     * @param taskBean
     * @return
     */
//    public static boolean checkTimingTaskValid(TimingTaskListBean taskBean) {
//        List<TimingTaskListBean> cacheList = getTimingTaskList();
//        if (cacheList.contains(taskBean)) { // 存在相同content
//            for (TimingTaskListBean listBean : cacheList) {
//                if (listBean.getTask().equals(taskBean.getTask())) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

    /**
     * 添加定时任务
     */
//    public static boolean addTimingTask(TimingTaskListBean taskBean) {
//
//        List<TimingTaskListBean> cacheList = getTimingTaskList();
//
//        if (cacheList.contains(taskBean)) { // 存在相同content
//            for (TimingTaskListBean listBean : cacheList) {
//                if (listBean.getTask() == taskBean.getTask()) {
//                    return false;
//                }
//            }
//        }
//        cacheList.add(taskBean);
//
//        getCache().put(KEY_BROADCAST_TASK_LIST, JSON.toJSONString(cacheList));
//        NursesBroadcastTaskLiveData.Companion.post(cacheList);
//        return true;
//    }

//    public static boolean removeTimingTask(TimingTaskListBean taskBean) {
//        List<TimingTaskListBean> cacheList = getTimingTaskList();
//        if (cacheList.contains(taskBean)) { // 存在相同content
//            for (TimingTaskListBean listBean : cacheList) {
//                if (listBean.getTask().equals(taskBean.getTask())) {
//                    LogUtils.d("删除定时任务->" + taskBean.toString());
//                    cacheList.remove(taskBean);
//                    NursesBroadcastTaskLiveData.Companion.post(cacheList);
//                    getCache().put(KEY_BROADCAST_TASK_LIST, JSON.toJSONString(cacheList));
//                    return true;
//                }
//            }
//        }
//        NursesBroadcastTaskLiveData.Companion.post(cacheList);
//        getCache().put(KEY_BROADCAST_TASK_LIST, JSON.toJSONString(cacheList));
//        return false;
//    }
}






