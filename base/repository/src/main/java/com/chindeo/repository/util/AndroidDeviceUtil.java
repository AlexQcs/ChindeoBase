package com.chindeo.repository.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 唯一标识码这东西在网络应用中非常有用，例如检测是否重复注册之类的。下面就来介绍几种标识码：
 * 1.DEVICE_ID；
 * 2.MAC ADDRESS；
 * 3.Sim Serial Number；
 * 4.Serial Number；
 * 5.ANDROID_ID；
 * 6.Installtion ID : UUID；
 * 7.DEVICE_ID:UUID;
 */

public class AndroidDeviceUtil {
    /**
     * DEVICE_ID  也称之为 IMEI
     * 概念： 是区别移动设备的标志，储存在移动设备中，可用于监控被窃或无效的移动设备。
     * 优点：
     * 1.根据不同的手机设备返回IMEI，MEID或者ESN码，唯一性良好 。
     * 不足：
     * 1.非手机：如平板电脑，像这样设备没有通话的硬件功能，系统中也就没有TELEPHONY_SERVICE，自然也就无法获得DEVICE_ID;
     * 2.权限问题：获取DEVICE_ID需要READ_PHONE_STATE权限；
     * 3.厂商定制系统中的Bug：少数手机设备上，由于该实现有漏洞，会返回垃圾，如:00000000或者****
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String IMEI = tm.getDeviceId();
        return IMEI;
    }


    public static boolean isAutoMac = true;
    public static String getMac(Context context) {
        String deviceId;
        if (isAutoMac) {
            boolean isInternet = AndroidDeviceUtil.isInternetConnected(context);
            if (isInternet) {
                deviceId = AndroidDeviceUtil.getWireMac();
            } else {
                deviceId = AndroidDeviceUtil.getWiFiMacAddress(context);
            }
        }else {
            deviceId = AndroidDeviceUtil.getWiFiMacAddress(context);
        }
//        Log.e("TAG", "getWireMac = " + AndroidDeviceUtil.getWireMac());
//        Log.e("TAG", "getWiFiMacAddress = " + AndroidDeviceUtil.getWiFiMacAddress(context));
        if (!TextUtils.isEmpty(deviceId)) {
            deviceId = deviceId.toUpperCase();
            deviceId = deviceId.replace(":", "").replace("：", "");
        }
        return deviceId;
    }


    /**
     * MAC ADDRESS
     * 概念：可以使用手机Wifi或蓝牙的MAC地址作为设备标识。
     * Wifi Mac关键代码：
     * 蓝牙 Mac关键代码：
     * 不足：
     * 1.如果设备没有支持WIFI的硬件，就返回null；
     * 2.如果设备没有支持蓝牙的硬件，就返回null。
     */

    public static String getWiFiMacAddress(Context context) {
        String wlanMac = null;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            try {
                WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wlanMac = wm.getConnectionInfo().getMacAddress();
            } catch (Exception e) {
                Log.e(AndroidDeviceUtil.class.getSimpleName(), e.getMessage());
            }
        } else {
            wlanMac = getWiFiMacAddress23();
        }
        String defaultMac1 = "00:00:00:00:00:00";
        String defaultMac2 = "02:00:00:00:00:00";

        if (wlanMac != null && !wlanMac.equals(defaultMac1) && !wlanMac.equals(defaultMac2)) {
            wlanMac = getWiFiMacAddress23();
        }
        return wlanMac;
    }

    //系统版本23时获取wifi地址
    public static String getWiFiMacAddress23() {
        String wlanMac = null;
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    break;
                }
                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }
                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                wlanMac = res1.toString();
            }
        } catch (Exception e) {
            Log.e(AndroidDeviceUtil.class.getSimpleName(), e.getMessage());
        }
        return wlanMac;
    }


    //得到有线网卡的MAC地址
    public static String getWireMac() {
        String strMacAddress = null;
        try {
            byte[] b = NetworkInterface.getByName("eth0")
                    .getHardwareAddress();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < b.length; i++) {
                if (i != 0) {
                    buffer.append(':');
                }
                System.out.println("b:" + (b[i] & 0xFF));
                String str = Integer.toHexString(b[i] & 0xFF);
                buffer.append(str.length() == 1 ? 0 + str : str);
            }
            strMacAddress = buffer.toString().toUpperCase();
            Log.d("TAG", strMacAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strMacAddress;
    }

    /**
     * 判断以太网网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isInternetConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if(mConnectivityManager !=null) {
                NetworkInfo mInternetNetWorkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
                boolean hasInternet = !isNullObject(mInternetNetWorkInfo) && mInternetNetWorkInfo.isConnected() && mInternetNetWorkInfo.isAvailable();
                return hasInternet;
            }
        }
        return false;
    }

    /**
     * 判断对象是否为空
     *
     * @param object
     * @return
     */
    public static boolean isNullObject(Object object) {

        if (object == null) {
            return true;
        }

        return false;
    }

    /**
     * Sim Serial Number
     * 概念：SIM卡的序列号。  简称为SN码
     * 不足：
     * 1.没有装Sim卡时，返回null；
     * 2.对于CDMA设备，返回null。
     */
    public static String getSimSerialNumber(Context context) {
        String simSerialNumber = null;
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            simSerialNumber = tm.getSimSerialNumber();
        } catch (Exception e) {
            Log.e(AndroidDeviceUtil.class.getSimpleName(), e.getMessage());
        }
        return simSerialNumber;
    }

    /**
     * Android Serial Number  硬件序列号
     * 概念：Android系统2.3版本以上可以获取硬件Serial Number。
     * 优点：非手机设备也可以通过该接口获取ID
     */

    public static String getAndroidSerialNumber() {
        String androidSerialNumber = null;
        try {
            androidSerialNumber = Build.SERIAL;
        } catch (Exception e) {
            Log.e(AndroidDeviceUtil.class.getSimpleName(), e.getMessage());
        }
        return androidSerialNumber;
    }

    /**
     * ANDROID_ID
     * 概念：当设备首次启动时，系统会随机生成一个64位的数字，并把这个数字以16进制字符串的形式保存下来。
     * 关键代码：
     * 不足：
     * 1.它在Android <=2.1 or Android >=2.3的版本是可靠、稳定的，但在2.2的版本并不是100%可靠的；
     * 2.在主流厂商生产的设备上，有一个很经常的bug，就是每个设备都会产生相同的ANDROID_ID。
     */

    public static String getAndroidId(Context context) {
        String androidId = null;
        try {
            androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            Log.e(AndroidDeviceUtil.class.getSimpleName(), e.getMessage());
        }
        return androidId;
    }


    public static class MobileDeviceInfo {
//        info =
//                "本机ip：" + getLocalHostIp()
//                            + "\n硬件制造商：" + Build.MANUFACTURER
//                            + "\n主板：" + Build.BOARD
//                            + "\n版本(手机型号)：" + Build.MODEL
//                            + "\n系统定制商：" + Build.BRAND
//                            + "\n硬件制造商：" + Build.MANUFACTURER
//                            + "\n手机制造商：" + Build.PRODUCT
//                            + "\n硬件识别码：" + Build.FINGERPRINT
//                            + "\n硬件名称：" + Build.HARDWARE
//                            + "\n硬件序列号：" + Build.SERIAL
//        ;
//        StringBuffer stringBuffer1 = new StringBuffer();
//            stringBuffer1.append("\nWIFI MAC：" + getWiFiMacAddress(context));
//            stringBuffer1.append("\nBluetooth MAC：" + getBluetoothMacAddress(context));
//            stringBuffer1.append("\nDevice ID：" + AndroidDeviceUtil.getDeviceId(context));
//            stringBuffer1.append("\nSim Serial Number：" + AndroidDeviceUtil.getSimSerialNumber(context));
//            stringBuffer1.append("\nAndroid Serial Number：" + getAndroidSerialNumber());
//            stringBuffer1.append("\n主板：" + Build.BOARD);
//            stringBuffer1.append("\n系统启动程序版本号：" + Build.BOOTLOADER);
//            stringBuffer1.append("\n系统定制商：" + Build.BRAND);
//            stringBuffer1.append("\ncpu指令集：" + Build.CPU_ABI);
//            stringBuffer1.append("\ncpu指令集2：" + Build.CPU_ABI2);
//            stringBuffer1.append("\n设置参数：" + Build.DEVICE);
//            stringBuffer1.append("\n显示屏参数：" + Build.DISPLAY);
//            stringBuffer1.append("\n无线电固件版本：" + Build.getRadioVersion());
//            stringBuffer1.append("\n硬件识别码：" + Build.FINGERPRINT);
//            stringBuffer1.append("\n硬件名称：" + Build.HARDWARE);
//            stringBuffer1.append("\nHOST:" + Build.HOST);
//            stringBuffer1.append("\n修订版本列表：" + Build.ID);
//            stringBuffer1.append("\n硬件制造商：" + Build.MANUFACTURER);
//            stringBuffer1.append("\n版本(手机型号)：" + Build.MODEL);
//            stringBuffer1.append("\n硬件序列号：" + Build.SERIAL);
//            stringBuffer1.append("\n手机制造商：" + Build.PRODUCT);
//            stringBuffer1.append("\n描述Build的标签：" + Build.TAGS);
//            stringBuffer1.append("\nTIME:" + Build.TIME);
//            stringBuffer1.append("\nbuilder类型：" + Build.TYPE);
//            stringBuffer1.append("\nUSER:" + Build.USER);
//            LogUtil.err(MobileDeviceInfo.class, stringBuffer1.toString());
//StringBuffer stringBuffer2 = new StringBuffer();
//                stringBuffer2.append("SIM序列号：" + simSerialNumber);
//                stringBuffer2.append("\nSIM卡状态：" + simState);
//                stringBuffer2.append("\n通讯模块设备码：" + deviceId);
//                stringBuffer2.append("\n通讯运营商id：" + subscriberId);
//                stringBuffer2.append("\n通讯运营商名称：" + subscriberName);
//                stringBuffer2.append("\n手机号码：" + mobileNumber);
//                LogUtil.err(MobileDeviceInfo.class, stringBuffer2.toString());
//
//                LogUtil.err(MobileDeviceInfo.class, "local:" + AndroidDeviceUtil.getLocalHostIp());
//                LogUtil.err(MobileDeviceInfo.class, "network:" + AndroidDeviceUtil.getNetworkIp());


        private String simState;         //移动电话通讯模块 sim卡的状态
        private String simSerialNumber;  //移动电话通讯模块 sim卡的序列号   也称之为 SN
        private String deviceId;         //移动电话通讯模块 设备码          也称之为 IMEI
        private String mobileNumber;     //手机号码  一般获取到的是首选手机号码
        private String subscriberId;     //运营商id    前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        private String subscriberName;   //运营商名称    前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        //---------------------
        private String mobileBrand;      //手机品牌 系统定制商

        public String getSimState() {
            return simState;
        }

        public String getSimSerialNumber() {
            return simSerialNumber;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public String getMobileNumber() {
            return mobileNumber;
        }

        public String getSubscriberId() {
            return subscriberId;
        }

        public String getSubscriberName() {
            return subscriberName;
        }

        public String getMobileBrand() {
            return mobileBrand;
        }

        public String getMobileBoard() {
            return mobileBoard;
        }

        public String getCpuAbi() {
            return cpuAbi;
        }

        public String getCpuAbi2() {
            return cpuAbi2;
        }

        public String getAndroidId() {
            return androidId;
        }

        public String getLocalIp() {
            return localIp;
        }


        private String mobileBoard;      //手机主板
        private String cpuAbi;           //手机Cpu 1 单双核
        private String cpuAbi2;          //手机Cpu 2
        private String androidId;          //手机Cpu 2
        //---------------------
        private String localIp;          //本地IP


        public MobileDeviceInfo(Context context) {
            mobileBoard = Build.BOARD;
            mobileBrand = Build.BRAND;
            cpuAbi = Build.CPU_ABI;
            cpuAbi2 = Build.CPU_ABI2;
            androidId = Build.SERIAL;

            localIp = AndroidDeviceUtil.getLocalHostIp();

            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                simSerialNumber = tm.getSimSerialNumber();
                mobileNumber = tm.getLine1Number();
                if (tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
                    simState = "正常";
                } else if (tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT) {
                    simState = "无SIM卡";
                } else if (tm.getSimState() == TelephonyManager.SIM_STATE_NETWORK_LOCKED) {
                    simState = "需要NETWORK PIN解锁";
                } else if (tm.getSimState() == TelephonyManager.SIM_STATE_PIN_REQUIRED) {
                    simState = "需要SIM卡的PIN解锁";
                } else if (tm.getSimState() == TelephonyManager.SIM_STATE_PUK_REQUIRED) {
                    simState = "需要SIM卡的PUK解锁";
                } else {
                    // SIM_STATE_UNKNOWN
                    simState = "SIM卡未知状态";
                }
                deviceId = tm.getDeviceId();
                subscriberId = tm.getSubscriberId();

                if (subscriberId != null) {
                    if (subscriberId.startsWith("46000") || subscriberId.startsWith("46002")) {
                        subscriberName = "中国移动";
                    } else if (subscriberId.startsWith("46001")) {
                        subscriberName = "中国联通";
                    } else if (subscriberId.startsWith("46003")) {
                        subscriberName = "中国电信";
                    }
                }

            } catch (Exception e) {
                Log.e(AndroidDeviceUtil.class.getSimpleName(), e.getMessage());
            }

        }
    }


    /**
     * 得到本机ip地址
     */
    public static String getLocalHostIp() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.e(AndroidDeviceUtil.class.getSimpleName(), e.getMessage());

        }
        return hostIp;

    }

    /**
     * 获取外网IP地址
     *
     * @return
     */
    public static String getNetworkIp() {
        URL infoUrl = null;
        InputStream inStream = null;
        String line = "";
        try {
            infoUrl = new URL("http://pv.sohu.com/cityjson?ie=utf-8");
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, "utf-8"));
                StringBuilder strber = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    strber.append(line + "\n");
                }
                inStream.close();
                // 从反馈的结果中提取出IP地址
                int start = strber.indexOf("{");
                int end = strber.indexOf("}");
                String json = strber.substring(start, end + 1);
                if (json != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        line = jsonObject.optString("cip");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }


    /**
     * 判断所有的IP地址
     *
     * @param ip
     * @return
     */
    public static String validIPAddressAll(String ip) {

        if (!ip.contains(".") && !ip.contains(":")) {
            return "Neither";
        }
        //如果是IPV4
        if (ip.contains(".")) {
            if (ip.endsWith(".")) {
                return "Neither";
            }
            String[] arr = ip.split("\\.");
            if (arr.length != 4) {
                return "Neither";
            }

            for (int i = 0; i < 4; i++) {
                if (arr[i].length() == 0 || arr[i].length() > 3) {
                    return "Neither";
                }
                for (int j = 0; j < arr[i].length(); j++) {
                    if (arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') {
                        continue;
                    }
                    return "Neither";
                }
                if (Integer.valueOf(arr[i]) > 255 || (arr[i].length() >= 2 && String.valueOf(arr[i]).startsWith("0"))) {
                    return "Neither";
                }
            }
            return "IPv4";
        }//如果是IPV4

        //如果是IPV6
        if (ip.contains(":")) {
            if (ip.endsWith(":") && !ip.endsWith("::")) {
                return "Neither";
            }
            //如果包含多个“::”，一个IPv6地址中只能出现一个“::”
            if (ip.indexOf("::") != -1 && ip.indexOf("::", ip.indexOf("::") + 2) != -1) {
                return "Neither";
            }

            //如果含有一个“::”
            if (ip.contains("::")) {
                String[] arr = ip.split(":");
                if (arr.length > 7 || arr.length < 1) {//"1::"是最短的字符串
                    return "Neither";
                }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].equals("")) {
                        continue;
                    }
                    if (arr[i].length() > 4) {
                        return "Neither";
                    }
                    for (int j = 0; j < arr[i].length(); j++) {
                        if ((arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') || (arr[i].charAt(j) >= 'A' && arr[i].charAt(j) <= 'F')
                                || (arr[i].charAt(j) >= 'a' && arr[i].charAt(j) <= 'f')) {
                            continue;
                        }
                        return "Neither";
                    }
                }
                return "IPv6";
            }

            //如果不含有“::”
            if (!ip.contains("::")) {
                String[] arr = ip.split(":");
                if (arr.length != 8) {
                    return "Neither";
                }
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i].length() > 4) {
                        return "Neither";
                    }
                    for (int j = 0; j < arr[i].length(); j++) {
                        if ((arr[i].charAt(j) >= '0' && arr[i].charAt(j) <= '9') || (arr[i].charAt(j) >= 'A' && arr[i].charAt(j) <= 'F')
                                || (arr[i].charAt(j) >= 'a' && arr[i].charAt(j) <= 'f')) {
                            continue;
                        }
                        return "Neither";
                    }
                }
                return "IPv6";
            }
        }//如果是IPV6
        return "Neither";
    }
}
