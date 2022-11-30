package com.chindeo.repository.mmkv.impl;

import android.text.TextUtils;
import android.util.Log;

import com.chindeo.repository.contants.RtcConstants;
import com.chindeo.repository.data.model.call.CallLogBean;
import com.chindeo.repository.mmkv.ICache;
import com.lazylibs.util.FastJsonUtils;
import com.lazylibs.util.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CallCache {

    //使用时间作为键 例如 sp_key_call_log_bean_yyyy-MM-dd
    public static final String SP_KEY_CALL_LOG_BEAN = "key_call_log_bean_";  //后面跟日期

    private static ICache getCache() {
        return ICache.create("call");
    }


    /**
     * @param type        当前未接电话类型
     * @param displayName 显示的名称
     * @param number      号码
     * @param isVideoCall 是否为视频通话
     */
    public static void addCallLog(RtcConstants type,String displayName,String number, boolean isVideoCall){
        if (!TextUtils.isEmpty(number)) {
            CallLogBean callLogBean = new CallLogBean();
            callLogBean.type = type.type;
            callLogBean.remoteUsername = TextUtils.isEmpty(displayName)? number : displayName;
            callLogBean.isVideoCall = isVideoCall;
            callLogBean.number = number;
            CallCache.addTodayCallLog(callLogBean);
            Log.e("CdoCallActivity", "addCallLog 记录保存成功");
        } else {
            Log.e("CdoCallActivity", "addCallLog 记录保存失败，号码为空");
        }
    }


    /**
     * 增加单条通话记录
     * 只有护士站主机才需要保存号码
     */
    public static void addTodayCallLog(CallLogBean callLogBean) {
        if (callLogBean == null) {
            return;
        }
        /*  if (AppModuleCache.getPrimaryAppModule() == AppModule.NURSE) {

        }*/
        List<CallLogBean> callLogToday = getCallLogToday();
        if (callLogToday == null || callLogToday.size() < 1) {
            callLogToday = new ArrayList<>();
        }
        callLogToday.add(0,callLogBean);

        getCache().put(SP_KEY_CALL_LOG_BEAN + TimeUtils.getTodayDate(), FastJsonUtils.list2Json(callLogToday));

    }
    public static void updatePdaCallLogRead(String number) {

        boolean isThereAnUpdate = false;
        boolean isFirst = true;
        if (!TextUtils.isEmpty(number)) {
            List<CallLogBean> callLogToday = getCallLogToday();

            ListIterator<CallLogBean> it = callLogToday.listIterator();

            while (it.hasNext()) {
                CallLogBean callLogBean = it.next();
                if (callLogBean.number.equals(number)) {
                    if (isFirst) {
                        callLogBean.setReply(true);
                        callLogBean.setCount(0);
                        isFirst=false;
                    }else {
                        it.remove();
                    }
                    isThereAnUpdate = true;
                }
            }
            if (isThereAnUpdate) {
                getCache().put(SP_KEY_CALL_LOG_BEAN + TimeUtils.getTodayDate(),
                        FastJsonUtils.list2Json(callLogToday));
            }
        }

    }

    /**
     * 更新未读的号码
     * 只有护士站主机才需要保存号码
     *
     * @param number 更新某个号码的通话记录为已读状态
     */
    public static void updateCallLogRead(String number) {
        //用标识是否有更新
//        if (AppModuleCache.getPrimaryAppModule() == AppModule.NURSE) {
//
//        }
        boolean isThereAnUpdate = false;
        if (!TextUtils.isEmpty(number)) {
            List<CallLogBean> callLogToday = getCallLogToday();
            for (CallLogBean callLog : callLogToday) {
                if (callLog.number.equals(number)) {
                    callLog.isReply = true;
                    isThereAnUpdate = true;
                }
            }
            if (isThereAnUpdate) {
                getCache().put(SP_KEY_CALL_LOG_BEAN + TimeUtils.getTodayDate(),
                        FastJsonUtils.list2Json(callLogToday));
            }
        }

    }


    //获取今天的通话记录
    public static List<CallLogBean> getCallLogToday() {
        return getCallLogByDate(TimeUtils.getTodayDate());
    }


    //根据日期获取通话记录
    public static List<CallLogBean> getCallLogByDate(String date) {
        String data = getCache().getString(SP_KEY_CALL_LOG_BEAN+date);
        if (!TextUtils.isEmpty(data)) {
            return FastJsonUtils.getJsonToList(data, CallLogBean.class);
        }
        return new ArrayList<>();
    }

}
