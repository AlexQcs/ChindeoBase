package com.chindeo.util;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

public class SystemUtil {

    private static String YOUNG_FEEL = "YoungFeel";
    // //浩然新设备 门旁新设备 床旁
    private static String C129_V1 = "c129_v1";
    // //浩然新设备 门旁新设备 床旁
    private static String c229 = "c229";
    // //浩然新设备 门旁新设备 门旁
    private static String C130_LP4 = "C130_LP4";

    /**
     * @return 是否需要镜像 只判断自己  YF3566_XXXD 胜创门旁    YF3568_XXXE胜创主机
     */
    public static Boolean isMirror() {

        boolean isMirror = true;
        //胜创大主机需要镜像
        if (YOUNG_FEEL.equalsIgnoreCase(Build.DEVICE) && "YF3568_XXXE".equalsIgnoreCase(Build.PRODUCT)) {

        } else if (YOUNG_FEEL.equalsIgnoreCase(Build.DEVICE)) {
            isMirror = false;
        }
        Log.d("TAG", "是否需要镜像 == " + Build.DEVICE + " -- " + Build.PRODUCT + "  -- " + isMirror);
        return isMirror;
    }

    /**
     * @return 是否胜创设备
     */
    public static Boolean isScDevice() {
        if (TextUtils.equals(Build.DEVICE, YOUNG_FEEL)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return 是否是浩然新设备
     */
    public static Boolean isBd() {
        Log.e("TAG", "isBd == " + Build.DEVICE);
        if (Build.DEVICE.contains(C129_V1) || Build.DEVICE.contains(C130_LP4) || Build.DEVICE.contains(c229)) {
            return true;
        } else {
            return false;
        }
    }
}
