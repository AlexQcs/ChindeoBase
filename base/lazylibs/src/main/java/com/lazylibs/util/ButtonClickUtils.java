package com.lazylibs.util;

/**
 * Created by leo
 * on 2019/11/15.
 * 防止快速点击
 */
public class ButtonClickUtils {

    private ButtonClickUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    // 两次点击按钮之间的点击间隔不能少于1000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 500;
    private static final int MIN_CLICK_DELAY_TIME_1500 = 1500;
    private static final int MIN_CLICK_DELAY_TIME_3000 = 3000;
    private static final int MIN_CLICK_DELAY_TIME_5000 = 5000;
    private static final int MIN_CLICK_DELAY_TIME_30S = 30 * 1000;
    private static final int MIN_CLICK_DELAY_TIME_60S = 60 * 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }

    public static boolean isFastClick1500() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME_1500) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }

    public static boolean isFastClick3000() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME_3000) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }

    public static boolean isFastClick5000() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME_5000) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }

    public static boolean isFastClick30s() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME_30S) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }

    public static boolean isFastClick60s() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < MIN_CLICK_DELAY_TIME_60S) {
            flag = true;
        } else {
            lastClickTime = curClickTime;
        }
        return flag;
    }
}
