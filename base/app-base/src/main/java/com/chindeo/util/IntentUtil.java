package com.chindeo.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.List;

/**
 * 意图工具
 * //    1.   ACTION_ACCESSIBILITY_SETTINGS ：    // 跳转系统的辅助功能界面
 * //    2.   ACTION_ADD_ACCOUNT ：               // 显示添加帐户创建一个新的帐户屏幕。【测试跳转到微信登录界面】
 * //    3.   ACTION_AIRPLANE_MODE_SETTINGS：     // 飞行模式，无线网和网络设置界面
 * //    或者：
 * //    ACTION_WIRELESS_SETTINGS  ：
 * //    4.    ACTION_APN_SETTINGS：                                //  跳转 APN设置界面
 * //    5.   【需要参数】 ACTION_APPLICATION_DETAILS_SETTINGS：
 * //    Uri packageURI = Uri.parse("package:" + "com.tencent.WBlog");
 * //    Intent intent =  new Intent(ACTION_APPLICATION_DETAILS_SETTINGS,packageURI);
 * //    startActivity(intent);                                     // 根据包名跳转到系统自带的应用程序信息界面
 * //    6.    ACTION_APPLICATION_DEVELOPMENT_SETTINGS :            // 跳转开发人员选项界面
 * //    7.    ACTION_APPLICATION_SETTINGS ：                       // 跳转应用程序列表界面
 * //    或者：
 * //    ACTION_MANAGE_ALL_APPLICATIONS_SETTINGS                    // 跳转到应用程序界面【所有的】
 * //    或者：
 * //    ACTION_MANAGE_APPLICATIONS_SETTINGS  ：                    // 跳转 应用程序列表界面【已安装的】
 * //    8.    ACTION_BLUETOOTH_SETTINGS  ：                        // 跳转系统的蓝牙设置界面
 * //    9.    ACTION_DATA_ROAMING_SETTINGS ：                      // 跳转到移动网络设置界面
 * //    10.    ACTION_DATE_SETTINGS ：                             // 跳转日期时间设置界面
 * //    11.    ACTION_DEVICE_INFO_SETTINGS  ：                     // 跳转手机状态界面
 * //    12.    ACTION_DISPLAY_SETTINGS  ：                         // 跳转手机显示界面
 * //    13.    ACTION_DREAM_SETTINGS                              【API 18及以上 没测试】
 * //    14.    ACTION_INPUT_METHOD_SETTINGS ：                     // 跳转语言和输入设备
 * //    15.    ACTION_INPUT_METHOD_SUBTYPE_SETTINGS                //【API 11及以上】 跳转 语言选择界面 【多国语言选择】
 * //    16.    ACTION_INTERNAL_STORAGE_SETTINGS                    // 跳转存储设置界面【内部存储】
 * //    或者：
 * //    ACTION_MEMORY_CARD_SETTINGS    ：                          // 跳转 存储设置 【记忆卡存储】
 * //    17.    ACTION_LOCALE_SETTINGS  ：                          // 跳转语言选择界面【仅有English 和 中文两种选择】
 * //    18.     ACTION_LOCATION_SOURCE_SETTINGS :                  // 跳转位置服务界面【管理已安装的应用程序。】
 * //    19.    ACTION_NETWORK_OPERATOR_SETTINGS ：                 // 跳转到 显示设置选择网络运营商。
 * //    20.    ACTION_NFCSHARING_SETTINGS  ：                      // 显示NFC共享设置。 【API 14及以上】
 * //    21.    ACTION_NFC_SETTINGS  ：                             // 显示NFC设置。这显示了用户界面,允许NFC打开或关闭。  【API 16及以上】
 * //    22.    ACTION_PRIVACY_SETTINGS ：                          // 跳转到备份和重置界面
 * //    23.    ACTION_QUICK_LAUNCH_SETTINGS  ：                    // 跳转快速启动设置界面
 * //    24.    ACTION_SEARCH_SETTINGS    ：                        // 跳转到 搜索设置界面
 * //    25.    ACTION_SECURITY_SETTINGS  ：                        // 跳转到安全设置界面
 * //    26.    ACTION_SETTINGS   ：                                // 跳转到设置界面
 * //    27.   ACTION_SOUND_SETTINGS                                // 跳转到声音设置界面
 * //    28.   ACTION_SYNC_SETTINGS ：                              // 跳转账户同步界面
 * //    29.     ACTION_USER_DICTIONARY_SETTINGS ：                 // 跳转用户字典界面
 * //    30.     ACTION_WIFI_IP_SETTINGS  ：                        // 跳转到IP设定界面
 * //    31.     ACTION_WIFI_SETTINGS  ：                           // 跳转Wifi列表设置
 */

public class IntentUtil {


    //亿莱顿设备隐藏状态栏,传入action
    public static void hideYLDBar(Context context) {
        Intent intent = new Intent();
        intent.setAction("elc.view.hide");
        context.sendBroadcast(intent);
    }

    //亿莱顿设备显示状态栏,传入action
    public static void showYLDBar(Context context) {
        Intent intent = new Intent();
        intent.setAction("elc.view.show");
        context.sendBroadcast(intent);
    }

    // 跳转到此App的系统权限设置界面 ，api大于11
    public static void goAppDetailSetting(Context context) {
        Intent localIntent = new Intent();
        addFlags(localIntent, context);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }

    // 跳转到此App的系统权限设置界面 ，api大于11
    public static void goAppDetailSettingNew(Context context) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        addFlags(intent, context);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
    }

    //当Android系统版本低于22就加上             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    private static void addFlags(Intent intent, Context context) {
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
    }

    // 跳转到某个ActivityForResult
    public static void startActivityForResult(Activity context, Class<? extends Activity> clazz, int requestCode) {
        Intent intent = new Intent(context, clazz);
        addFlags(intent, context);
        context.startActivityForResult(intent, requestCode);
    }

    //跳转到系统界面,传入action
    public static void startActivity(Context context, String action) {
        Intent intent = new Intent(action);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    //跳转到系统界面,传入action 和data
    public static void startActivity(Context context, String action, Uri data) {
        Intent intent = new Intent(action, data);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    //跳转到系统设置
    public static void goSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    //跳转到系统的日期设置
    public static void goDateSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_DATE_SETTINGS);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    //跳转到系统的移动网络设置
    public static void goRoamingSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
        addFlags(intent, context);
        context.startActivity(new Intent(intent));
    }

    //跳转到系统的WiFi设置
    public static void goWiFiSettings(Context context) {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        addFlags(intent, context);
        context.startActivity(new Intent(intent));
    }



    /**
     * 启动一个Activity
     *
     * @param context        from
     * @param targetActivity to
     */
    public static void startActivity(Context context, Class<? extends Activity> targetActivity) {
        Intent intent = new Intent(context, targetActivity);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    /**
     * 启动一个Activity
     *
     * @param context        from
     * @param targetActivity to
     * @param extras         Bundle 数据
     */
    public static void startActivity(final Context context, final Class<? extends Activity> targetActivity, final Bundle extras) {
        Intent intent = new Intent(context, targetActivity);
        intent.putExtras(extras);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    /**
     * 跳转到拨打电话界面
     */
    public static void dialPhone(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    /**
     * 直接拨打电话
     */
    public static void callPhone(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNumber);
        intent.setData(data);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    /**
     * <uses-permission android:name="android.permission.SEND_SMS"></uses-permission>
     * <uses-permission android:name="android.permission.READ_SMS"></uses-permission>
     * <uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission>
     * 发送短信
     */
    public static void goSMS(Context context, String message) {

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.putExtra("sms_body", message);
        sendIntent.setType("vnd.android-dir/mms-sms");
        //sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sendIntent);
    }

    /**
     * <uses-permission android:name="android.permission.SEND_SMS"></uses-permission>
     * <uses-permission android:name="android.permission.READ_SMS"></uses-permission>
     * <uses-permission android:name="android.permission.RECEIVE_SMS"></uses-permission>
     * 发送短信
     */
    public static void goToSMS(Context context, String phoneNumber, String message) {
        //这个方法自动设置接收方的号码
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent sendIntent = new Intent(Intent.ACTION_VIEW, uri);
        //sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        sendIntent.putExtra("sms_body", message);
        context.startActivity(sendIntent);
    }

    /**
     * 跳转到浏览器
     */
    public static void goBrowserHttp(Context context, String url) {
        Uri uri = Uri.parse("http://" + url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        addFlags(intent, context);
        context.startActivity(intent);
    }

    /**
     * 跳转到浏览器
     */
    public static void goBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        addFlags(intent, context);
        context.startActivity(intent);
    }
/**====================跳转=====================**/


    /**
     * 使用系统相册选择图片
     */
    public static void imageSelect(Activity currentActivity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        addFlags(intent, currentActivity);
        currentActivity.startActivityForResult(intent, requestCode);
    }

    /**
     * 使用系统相册选择图片之后裁截取图片
     */
    public static void imageSelectZoom(Activity aty, Uri uri, int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        aty.startActivityForResult(intent, requestCode);
    }

    /**
     * 播放视频
     *
     * @param context
     * @param videoPath
     */
    public static void goVideoPaly(Context context, String videoPath) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String bPath = "file://" + videoPath;
        intent.setDataAndType(Uri.parse(bPath), "video/mp4");
        addFlags(intent, context);
        context.startActivity(intent);
    }

    /**
     * 跳转到微信
     */
    public static void goWeiXin(Context context) {
        try {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ComponentName cmp = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            addFlags(intent, context);
            intent.setComponent(cmp);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "微信未安装", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 跳转到qq
     *
     * @param qqNumber 和某人的对话
     */
    public static void goQQ(Context context, String qqNumber) {
        try {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNumber;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            addFlags(intent, context);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "QQ未安装", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 结合跳转
     * 1.先普通打开
     * 2.如果普通打开失败，再使用第二种方法打开。
     */
    public static String goAppCombination(Context context, String packageName) {
        String result = goAppNormal(context, packageName);
        if (!TextUtils.isEmpty(result)) {
            result = goApp(context, packageName);
        }
        return result;
    }


    /**
     * 通过指定协议跳转到某个应用
     *
     * @param packageName 应用包名
     *                    动作
     *                    <action android:name="com.chindeo.call.app" />
     *                    指定启动协议
     *                    <data android:scheme="call" />
     */
    public static String goAppScheme(Context context, String packageName, String schemeName, String schemeValue) {
        String functionName = "go app scheme : ";
        if (context == null) {
            return functionName + "context == null";
        }
        if (TextUtils.isEmpty(packageName)) {
            return functionName + "TextUtils.isEmpty(packageName)";
        }
        if (TextUtils.isEmpty(schemeName)) {
            return functionName + "TextUtils.isEmpty(schemeName)";
        }
        try {
            IntentUtil.startActivity(context, packageName, Uri.parse(schemeName + ":" + schemeValue));
        } catch (Exception e) {
            return functionName + e.getMessage();
        }
        return null;
    }

    /**
     * 得到系统启动的Intent，普通跳转到某个应用
     *
     * @param packageName 应用包名
     */
    public static String goAppNormal(Context context, String packageName) {
        String functionName = "go app normal : ";
        if (context == null) {
            return functionName + "context == null";
        }
        if (TextUtils.isEmpty(packageName)) {
            return functionName + "TextUtils.isEmpty(packageName)";
        }
        PackageManager mPacManager = context.getPackageManager();
        Intent intent = null;
        try {
            intent = mPacManager.getLaunchIntentForPackage(packageName);
        } catch (Exception e) {
            return functionName + e.getMessage();
        }
        if (intent == null) {
            return functionName + "intent == null (mPacManager.getLaunchIntentForPackage(packageName))";
        }
        addFlags(intent, context);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            return functionName + e.getMessage();
        }
        return null;
    }

    /**
     * 通过系统属性拿到PackageInfo得到app启动的主类名，跳转到某个应用
     *
     * @param packageName 应用包名
     */
    public static String goApp(Context context, String packageName) {

        String functionName = "go app : ";
        if (context == null) {
            return functionName + "context == null";
        }
        if (TextUtils.isEmpty(packageName)) {
            return functionName + "TextUtils.isEmpty(packageName)";
        }
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return functionName + e.getMessage();
        }
        if (packageinfo == null) {
            return functionName + packageName + " no installed";
        }
        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);
        // 通过getPackageManager()的queryIntentActivities方法遍历
        ResolveInfo resolveInfo = null;
        try {
            List<ResolveInfo> resolveInfoList = context.getPackageManager().queryIntentActivities(resolveIntent, 0);
            resolveInfo = resolveInfoList.iterator().next();
        } catch (Exception e) {
            e.printStackTrace();
            return functionName + e.getMessage();
        }
        if (resolveInfo == null) {
            return functionName + "resolveInfo == null";
        }
        String _packageName = resolveInfo.activityInfo.packageName;
        // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
        String className = resolveInfo.activityInfo.name;
        // LAUNCHER Intent
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // 设置ComponentName参数1:packagename参数2:MainActivity路径
        try {
            ComponentName cn = new ComponentName(_packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return functionName + e.getMessage();
        }
        return null;
    }

    /**
     * 启动其他app的服务
     */
    public static void startAppService1(Context context, String packageName, String serviceName) {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //String packageName = "com.ang.chapter_2_service"; //另一个app的包名
        //String className = "com.ang.chapter_2.poolBinder.BinderPoolService"; //另一个app要启动的组件的全路径名
        intent.setClassName(packageName, serviceName);
        context.startService(intent);//或者bindService(intent, mConnection, Context.BIND_AUTO_CREATE); 都能启动

    }

    /**
     * 启动其他app的服务
     */
    public static void startAppService(Context context, String packageName, String serviceName) {
        ComponentName componetName = new ComponentName(
                packageName,  //这个参数是另外一个app的包名
                serviceName);   //这个是要启动的Service的全路径名
        Intent intent = new Intent();
        intent.setComponent(componetName);
        context.startService(intent); //或者bindService(intent, mConnection,Context.BIND_AUTO_CREATE);

    }

    /**
     * 返回到手机桌面
     */
    public static void goHome(Context context) {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        addFlags(homeIntent, context);
        context.startActivity(homeIntent);
    }

/**====================安装=====================**/

    /**
     * 通过APK地址获取此APP的包名和版本等信息 
     */
    public static String getApkPackageName(Context context, String apkPath) {
        //        String FilePath="*.apk";//输入APK地址
        String packageName = null;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                ApplicationInfo appInfo = info.applicationInfo;
                packageName = appInfo.packageName;                      //获取安装包名称 
                String versionName = info.versionName;                  //获取版本信息 
                Log.d("getApkPackageName", "包名是：" + packageName);
                Log.d("getApkPackageName", "版本Name：" + versionName);
            }
        } catch (Exception ignored) {

        }

        return packageName;
    }

    /**
     * 检测某个应用是否安装
     *
     * @param context
     * @param packageName
     * @return boolean
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 启动某App  默认是主页面
     *
     * @param context
     */
    public static void launchApp(Context context, String appPackageName) {
        // 判断是否安装过App，否则去下载
        if (isAppInstalled(context, appPackageName)) {
            context.startActivity(context.getPackageManager().getLaunchIntentForPackage(appPackageName));
        } else {
            //下载
            Toast.makeText(context, appPackageName + "未安装", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 启动某App某页面 （方法1）
     * 调用此方法前请注意在 被启动 的App中的AndroidManifest activity 加入 <intent-filter> <action /> 为当前类全名
     *
     * @param context
     */
    public static void launchAppActivity(Context context,
                                         String packageName,
                                         String className,
                                         String classAction) {
        if (isAppInstalled(context, packageName)) {
            Intent intent = new Intent();
            ComponentName comp = new ComponentName(packageName, className);
            intent.setComponent(comp);
            addFlags(intent, context);
            intent.setAction(classAction);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, packageName + "未安装", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 启动某App某页面 （方法2）
     * 调用此方法前请注意在 被启动 的App中的AndroidManifest activity 加入 <intent-filter> <action /> 为当前类全名
     *
     * @param context
     */
    public static void launchAppActivity1(Context context, String appPackageName, String appActivityName) {
        if (isAppInstalled(context, appPackageName)) {
            Intent intent = new Intent();
            //包名 包名+类名（全路径）
            addFlags(intent, context);
            intent.setClassName(appPackageName, appActivityName);
            intent.setAction(appActivityName);
            context.startActivity(intent);
        } else {
            //下载
            Toast.makeText(context, appPackageName + "未安装", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 安装一个apk
     * 需要new_task
     * 7.0以后需要在清单文件做 provider 声明，
     *
     * @param apkPath apk所在目录的绝对路径  比如imsandroid/imsapp0208.apk
     */
    public static void installApk(Context mContext, String applicationId, String apkPath) {
        Log.e("installApk", applicationId);
        //String fileName = Environment.getExternalStorageDirectory() +"/"+ apkName;
        File file = new File(apkPath);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(mContext, applicationId + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);

    }

    /**
     * 卸载一个APK
     *
     * @param packageName
     */
    public static void uninstallApk(Context context, String packageName) {
        Uri packageURI = Uri.parse("package:" + packageName);
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        uninstallIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(uninstallIntent);
    }

    //判断应用是否在运行
    public static boolean checkAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(packageName) || info.baseActivity.getPackageName().equals(packageName)) {
                isAppRunning = true;
                //Log.i(TAG,info.topActivity.getPackageName() + " info.baseActivity.getPackageName()="+info.baseActivity.getPackageName());
                break;
            }
        }
        return isAppRunning;
    }
}
