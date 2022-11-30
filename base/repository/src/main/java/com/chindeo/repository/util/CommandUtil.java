package com.chindeo.repository.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.chindeo.repository.contants.FilePathConstants;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class CommandUtil {
    public final static String COMMAND_REBOOT = "reboot";
    public final static String COMMAND_FORCE_STOP = "am force-stop ";
    public final static String TAG = CommandUtil.class.getSimpleName();


    public static void reboot() {
        CommandResult commandResult = CommandUtil.executeCommand("reboot");
    }

    public static void openAccessbilityService(Context context) {
        String[] commands = new String[]{
                "settings put secure enabled_accessibility_services " + context.getPackageName() + "/com.chindeo.launcher.service.InstallAccessibilityService",
                "settings put secure accessibility_enabled 1"
        };
        CommandResult commandResult = CommandUtil.executeCommand(commands);
    }

    public static void openInstallFromUnknown() {
        CommandResult commandResult = CommandUtil.executeCommand("settings put secure install_non_market_apps 1");
    }

    //============================通用============================
    //获取wifi无线mac地址
    public static String getWifiMacAddress() {
        //wifi无线
        CommandResult commandResult = CommandUtil.executeCommand("cat /sys/class/net/wlan0/address");
        String macResult = commandResult.successMsg;
        if (!TextUtils.isEmpty(macResult)) {
            macResult = macResult.toUpperCase();
            macResult = macResult.replace(":", "").replace("：", "");
        }
        return macResult;
    }

    //获取网卡有线mac地址
    public static String getEthMac() {
        String macResult = "";
        //网卡有线
        CommandResult commandResult = CommandUtil.executeCommand("cat /sys/class/net/eth0/address");
        macResult = commandResult.successMsg;
        Log.d("zhongcg", "# macResult /sys/class/net/eth0/address->>>> " + macResult);
        if (TextUtils.isEmpty(macResult)) {
            macResult = CommandUtil.getWifiMacAddress();
        }
        if (!TextUtils.isEmpty(macResult)) {
            macResult = macResult.toUpperCase();
            macResult = macResult.replace(":", "").replace("：", "");
        }
        return macResult;
    }

    //开启WifiAdb
    public static void openWifiAdb() {
            CommandResult commandWifiAdb = CommandUtil.executeCommand("setprop service.adb.tcp.port 5555");
    }

    /**
     * 静默安装 通用
     */
    public static boolean executeClientInstall(String apkPath) {
        PrintWriter PrintWriter = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(checkRooted() ? COMMAND_SU : COMMAND_SH);
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.println("chmod 777 " + apkPath);
            PrintWriter.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib");
            PrintWriter.println("pm install -r " + apkPath);
//          PrintWriter.println("exit");
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "clientInstall 异常：" + e.getMessage());

        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    /**
     * 静默卸载 通用
     */
    public static boolean executeClientUninstall(String packageName) {
        PrintWriter PrintWriter = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(checkRooted() ? COMMAND_SU : COMMAND_SH);
            PrintWriter = new PrintWriter(process.getOutputStream());
            PrintWriter.println("LD_LIBRARY_PATH=/vendor/lib:/system/lib ");
            PrintWriter.println("pm uninstall " + packageName);
            PrintWriter.flush();
            PrintWriter.close();
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    private static boolean returnResult(int value) {
        // 代表成功
        if (value == 0) {
            return true;
        } else if (value == 1) { // 失败
            return false;
        } else { // 未知情况
            return false;
        }
    }

    /**
     * 启动app
     * com.exmaple.client/.MainActivity
     * com.exmaple.client/com.exmaple.client.MainActivity
     */
    public static boolean startApp(String packageName, String activityName) {
        String cmd = "am start -n " + packageName + "/" + activityName + " \n";
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            int value = process.waitFor();
            return returnResult(value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
        return false;
    }

    /**
     * 应用程序运行命令获取 Root权限，设备必须已破解(获得ROOT权限)
     *
     * @param pkgCodePath 为Context.getPackageCodePath()
     * @return 应用程序是/否获取Root权限
     */
    public static boolean upgradeRootPermission(String pkgCodePath) {
        Process process = null;
        DataOutputStream os = null;
        try {
            String cmd = "chmod 777 " + pkgCodePath;
            process = Runtime.getRuntime().exec("su"); //切换到root帐号
            os = new DataOutputStream(process.getOutputStream());
            os.writeBytes(cmd + "\n");
            os.writeBytes("exit\n");
            os.flush();
            process.waitFor();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                process.destroy();
            } catch (Exception e) {
            }
        }
        return true;
    }

    public final static String COMMAND_SU = "su";
    public final static String COMMAND_SH = "sh";
    public final static String COMMAND_EXIT = "exit\n";
    public final static String COMMAND_LINE_END = "\n";

    //是否取得root权限
    public static boolean checkRooted() {
        boolean result = false;
        try {
            result = new File("/system/bin/su").exists() || new File("/system/xbin/su").exists();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "checkRooted：" + e.getMessage());
        }
        return result;
    }

    /**
     * 执行结果
     */
    public static class CommandResult {
        public int result = -1;
        public String errorMsg;
        public String successMsg;

        @Override
        public String toString() {
            return "CommandResult{" +
                    "result=" + result +
                    ", errorMsg='" + errorMsg + '\'' +
                    ", successMsg='" + successMsg + '\'' +
                    '}';
        }
    }

    /**
     * 执行单条命令
     *
     * @param command
     * @return
     */
    public static CommandResult executeCommand(String command) {
        String[] commands = {command};
        return CommandUtil.executeCommand(commands);
    }

    /**
     * 执行多条命令
     *
     * @param commands
     * @return
     */
    public static CommandResult executeCommand(String[] commands) {
        CommandResult commandResult = new CommandResult();
        if (commands == null || commands.length == 0) return commandResult;
        Process process = null;
        DataOutputStream os = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        try {
            process = Runtime.getRuntime().exec(checkRooted() ? COMMAND_SU : COMMAND_SH);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command != null) {
                    os.write(command.getBytes());
                    os.writeBytes(COMMAND_LINE_END);
                    os.flush();
                }
            }
            os.writeBytes(COMMAND_EXIT);
            os.flush();
            commandResult.result = process.waitFor();
            // 获取错误信息
            successMsg = new StringBuilder();
            errorMsg = new StringBuilder();
            successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
            errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String s;
            while ((s = successResult.readLine()) != null) successMsg.append(s);
            while ((s = errorResult.readLine()) != null) errorMsg.append(s);
            commandResult.successMsg = successMsg.toString();
            commandResult.errorMsg = errorMsg.toString();
            Log.i(TAG, commandResult.result + " | " + commandResult.successMsg + " | " + commandResult.errorMsg);
        } catch (Exception e) {
            String errmsg = e.getMessage();
            if (errmsg != null) {
                Log.e(TAG, errmsg);
            } else {
                e.printStackTrace();
            }
        } finally {
            try {
                if (os != null) os.close();
                if (successResult != null) successResult.close();
                if (errorResult != null) errorResult.close();
            } catch (IOException e) {
                String errmsg = e.getMessage();
                if (errmsg != null) {
                    Log.e(TAG, errmsg);
                } else {
                    e.printStackTrace();
                }
            }
            if (process != null) process.destroy();
        }
        return commandResult;
    }
    //============================通用============================


    //============================天波============================

    public static void executeTb() {
        //开启adb 调试
        CommandUtil.executeCommandTb("setprop adb.network.port 5555");
        //setprop adb.network.port 5555 开启
        //setprop adb.network.port -1   关闭
        //开启第三方应用安装
        CommandUtil.executeCommandTb("setprop rw.telpo.isAllowInstall true");
    }

    public static boolean executeSilentInstallTb(String apkPath, String apkPackageName) {
        String cmd1 = "chmod 777 " + apkPath + "\n";
        String cmd2 = " pm uninstall -r -k " + apkPackageName + "\n";
        String cmd3 = " pm install -r " + apkPath + "\n";
        return CommandUtil.executeCommandTb(cmd1, cmd2, cmd3);
    }

    public static boolean executeCommandTb(String... cmdArray) {
        boolean isSuccess = false;
        Process process = null;
        OutputStream out = null;
        try {
            process = Runtime.getRuntime().exec(checkRooted() ? COMMAND_SU : COMMAND_SH);
            out = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            for (String cmd : cmdArray) {
                dataOutputStream.writeBytes(cmd);
            }
            dataOutputStream.flush(); // 提交命令
            dataOutputStream.close(); // 关闭流操作
            out.close();
            isSuccess = CommandUtil.waitForProcessTb(process);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "executeCommandTb：" + e.getMessage());
        }
        return isSuccess;
    }

    private static boolean waitForProcessTb(Process p) {
        boolean isSuccess = false;
        int returnCode;
        try {
            returnCode = p.waitFor();
            switch (returnCode) {
                case 0:
                    isSuccess = true;
                    break;
                default:
                case 1:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "waitForProcessTb：" + e.getMessage());
        }
        return isSuccess;
    }


    /**
     * 使用root命令截取屏幕图片
     * 保存位置：sdcard/chindeo_app/log/screen.png
     */
    public static void screenshot() {
        File file = new File(FilePathConstants.getDir(FilePathConstants.APP_IMAGE_DIR));
//        LogUtils.d("截图目录" + file.exists());
        if (!file.exists()) {
            file.mkdirs();
        }
        long startTime = System.currentTimeMillis();
//        LogUtils.d(CommandUtil.class.getSimpleName(), "使用root命令截取屏幕图片 开始");
        CommandUtil.executeCommand("/system/bin/screencap -p "
                + FilePathConstants.getDir(FilePathConstants.APP_SCREENSHOT));
//        LogUtils.d(CommandUtil.class.getSimpleName(), "使用root命令截取屏幕图片 完成耗时:"+ (System.currentTimeMillis() - startTime) + "ms");
    }

    //============================天波============================
}
