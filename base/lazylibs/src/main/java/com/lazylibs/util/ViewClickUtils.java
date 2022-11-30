package com.lazylibs.util;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

/**
 */
public final class ViewClickUtils {

    private final static int LAST_CLICK_TIME = 0x84382453;

    private final static int DEFAULT_CLICK_DURATION = 500;

    /**
     * 判断是不是连续在{@param duration}内快速点击
     *
     * @param duration 下次响应点击时间的时长默认是500毫秒
     * @return 是否能快速点击 true : 快速点击  false : 单击
     */
    public static boolean isFastClick(@NonNull View view, long duration) {
        duration = duration == 0L ? DEFAULT_CLICK_DURATION : duration;
        long currentTimeMillis = System.currentTimeMillis();
        long lastClickTime = view.getTag(LAST_CLICK_TIME) != null ?
                (long) view.getTag(LAST_CLICK_TIME) : 0L;
        if (lastClickTime > currentTimeMillis) {
            view.setTag(LAST_CLICK_TIME, 0L);
            return false;
        }
        if (currentTimeMillis - lastClickTime < duration) {
            return true;
        }
        view.setTag(LAST_CLICK_TIME, currentTimeMillis);
        return false;
    }

    /**
     * 判断是不是连续在0.5秒内快速点击
     *
     * @return 是否能快速点击 true : 快速点击  false : 单击
     */
    public static boolean isFastClick(@NonNull View view) {
        return isFastClick(view, 0L);
    }

    private static final int MIN_CLICK_DELAY_TIME_3000 = 3000;
    private static final int MIN_CLICK_DELAY_TIME_500 = 500;
    public static long lastClickTime;
    public static long lastClickTime3000;
    //探视防止关闭之后3秒点击
    public static boolean isFastClick3000() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        Log.e("TAG", "isFastClick3000 -- " + curClickTime + " ---  " + lastClickTime3000 + " 减 - " + (curClickTime - lastClickTime3000));
        if ((curClickTime - lastClickTime3000) < MIN_CLICK_DELAY_TIME_3000) {
            flag = true;
        } else {
            lastClickTime3000 = curClickTime;
        }

        return flag;
    }

    public static boolean isFastClick500() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME_500) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }

    private static final int MIN_CLICK_DELAY_TIME_1000 = 1000;
    public static long lastClickTime1000;
    //探视防止关闭之后3秒点击
    public static boolean isFastClick1000() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime1000) < DEFAULT_CLICK_DURATION) {
            flag = true;
        } else {
            lastClickTime1000 = curClickTime;
        }
        return flag;
    }

}
