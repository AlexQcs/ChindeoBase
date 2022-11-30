package com.chindeo.repository.contants;

import android.text.TextUtils;
import android.util.Log;

import com.chindeo.repository.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public enum AppModule {

    //启动器
    LAUNCHER("com.chindeo.launcher.app", "Launcher", "启动器", false),

    //床旁
    BED("com.chindeo.bed.app", "bed", "床旁", false),

    //护士站主机
    NURSE("com.chindeo.nursehost", "nurses", "护士站主机", false),

    //web app
    WEB("com.ktcp.launcher", "web", "webApp", false),

    //x5 web app
    WEB_X5("com.chindeo.webapp", "web_x5", "web_x5", false),

    //谷歌文字转语音
    TTS("com.google.android.tts", "tts", "谷歌文字转语音", true),

    //xWalk内核
    XWALK("org.xwalk.core", "xwalk", "xWalk内核", false),

    //音视频
    CALL("com.chindeo.call.app", "call", "音视频", true),

    //人脸
    FACE("com.chindeo.face.app", "face", "人脸", true),

    //推送
    WEB_SOCKET("com.chindeo.websocket.app", "websocket", "推送", true),

    //x5内核资源
    RESOURCES("com.chindeo.resources", "x5resources", "x5内核资源", true),

    //智能电视IPTV
    IPTV("com.chindeo.iptv", "iptv", "智能电视IPTV", true),

    //输入法
    INPUT("com.sohu.inputmethod.sogou.meizu", "input", "输入法", true),
    //走廊屏
    ZLP("com.ktcp.launcher.zlp", "zlp", "走廊屏", false),
    //输液监控
    SYJK("com.ktcp.launcher.syjk", "syjk", "输液监控", false),
    //移动护理
    YDHL("com.ktcp.launcher.ydhl", "ydhl","移动护理", false),

    //门旁屏
    MPP("com.ktcp.launcher.bfmpp", "mpp", "门旁屏", false),
    //叫号大屏
    JHDP("com.ktcp.launcher.jhdp", "jhdp", "叫号大屏", false),
    //手表
    WATCH("com.chindeo.watch.app", "watch", "手表", false),
    //pda
    PDA("com.chindeo.pda.app", "pda", "pda", false),


    //语音唤醒
    AWAKE("com.chindeo.iflytek.app", "awake", "语音唤醒", true),

    //直播
    LIVE("com.chindeo.live", "live", "直播", true),

    LARGE("com.chindeo.large.app", "large", "large", true),

    ;

    public final String packageName;
    public final boolean isPlugin;
    public final String appName;
    public final String appDesc;
//    public final int type; // 查询更新用到的type值;

    AppModule(String packageName, String appName, String appDesc, boolean isPlugin) {
        this.packageName = packageName;
        this.appName = appName;
        this.isPlugin = isPlugin;
        this.appDesc = appDesc;
    }

    public static AppModule getModule(String packageName) {
        if (BuildConfig.DEBUG && TextUtils.isEmpty(packageName)) {
//            throw new AssertionError("AppModule Assertion failed packageName is Not");
            Log.e(AppModule.class.getSimpleName(), "#AppModule Assertion failed packageName is Not !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            return null;
        }
        for (AppModule value : values()) {
            if (value.packageName.equals(packageName)) {
                return value;
            }
        }
        return null;
    }

    public static List<AppModule> getPluginModule() {
        List<AppModule> list = new ArrayList<>();
        for (AppModule value : values()) {
            if (value.isPlugin) {
                list.add(value);
            }
        }
        return list;
    }
}
