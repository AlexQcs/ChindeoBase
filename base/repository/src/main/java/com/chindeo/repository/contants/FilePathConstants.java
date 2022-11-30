package com.chindeo.repository.contants;

import android.os.Environment;

import java.io.File;

public class FilePathConstants {

    private static final String BASE_DIR = File.separator + "chindeo_app" + File.separator;
    public static final String APP_PRIMARY_FILE_DIR = BASE_DIR + "guard" + File.separator;
    public final static String APP_LOG_DIR = BASE_DIR + "log" + File.separator;                   //程序异常目录
    public final static String APP_IMAGE_DIR = BASE_DIR + "image" + File.separator;               //缓存图片文件夹（在主目录下）
    public final static String APP_DOWNLOAD = BASE_DIR + "download" + File.separator;            //客户端下载apk的目录
    public final static String APP_SOUND_RECORD = BASE_DIR + "soundRecord" + File.separator;     //音视频录音
    public final static String APP_INTERFACE = BASE_DIR + "interface.log";                       //接口异常记录
    public final static String APP_DEVICE_CHECK = BASE_DIR + "device_check.log";                  //设备自检记录
    public final static String APP_EXCEPTION = BASE_DIR + "exception.log";                       //保存程序异常的目录
    public final static String APP_FAULT = BASE_DIR + "fault.log";                               //保存各插件运行状态的目录
    public final static String APP_CALL_CONFIG = BASE_DIR + "app_call_config.json";              //音视频的配置信息
    public final static String APP_SCREENSHOT = APP_IMAGE_DIR + "screen.png";        //保存屏幕截图
    public final static String APP_SKIN_DIR = BASE_DIR + "skin" + File.separator; // 皮肤

    public static String getDir(String field) {
        return Environment.getExternalStorageDirectory().getPath() + field;
    }
}
