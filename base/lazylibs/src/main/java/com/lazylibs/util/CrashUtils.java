/*
 * Copyright 2017 Blankj
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lazylibs.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 */

public final class CrashUtils {

    private static String defaultDir;
    private static String dir;
    private static String packageName;// 添加包名作为保存文件名 - 20180126.linjp
    private static String versionName;
    private static int versionCode;

    private static ExecutorService sExecutor;

    private static final String FILE_SEP = System.getProperty("file.separator");
    // 添加包名作为保存文件名[yyyy-MM-dd HH-mm-ss -> yyyyMMdd.HHmm] - 20180126.linjp
    private static final Format FORMAT = new SimpleDateFormat("yyyyMMdd.HHmm", Locale.getDefault());

    private static String CRASH_HEAD;

    private static final Thread.UncaughtExceptionHandler DEFAULT_UNCAUGHT_EXCEPTION_HANDLER;
    private static final Thread.UncaughtExceptionHandler UNCAUGHT_EXCEPTION_HANDLER;

    static {
        DEFAULT_UNCAUGHT_EXCEPTION_HANDLER = Thread.getDefaultUncaughtExceptionHandler();

        UNCAUGHT_EXCEPTION_HANDLER = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(final Thread t, final Throwable e) {
                if (e == null) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                    return;
                }
                Date now = new Date(System.currentTimeMillis());
                String fileName = packageName + "." + FORMAT.format(now) + ".txt";// 添加包名作为保存文件名 - 20180126.linjp
                final String fullPath = (dir == null ? defaultDir : dir) + fileName;
                if (!createOrExistsFile(fullPath)) return;
                if (sExecutor == null) {
                    sExecutor = Executors.newSingleThreadExecutor();
                }
                sExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        PrintWriter pw = null;
                        try {
                            pw = new PrintWriter(new FileWriter(fullPath, false));
                            pw.write(CRASH_HEAD);
                            e.printStackTrace(pw);
                            Throwable cause = e.getCause();
                            while (cause != null) {
                                cause.printStackTrace(pw);
                                cause = cause.getCause();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (pw != null) {
                                pw.close();
                            }
                        }
                    }
                });
                if (DEFAULT_UNCAUGHT_EXCEPTION_HANDLER != null) {
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER.uncaughtException(t, e);
                }
            }
        };
    }

    private CrashUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     */
    public static void init(Context context) {
        init(context, "");
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir 崩溃文件存储目录
     */
    public static void init(Context context, @NonNull final File crashDir) {
        init(context, crashDir.getAbsolutePath());
    }

    /**
     * 初始化
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />}</p>
     *
     * @param crashDir 崩溃文件存储目录
     */
    public static void init(Context context, final String crashDir) {
        try {
            PackageInfo pi = context
                    .getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            packageName = context.getPackageName();// 添加包名作为保存文件名 - 20180126.linjp
            if (pi != null) {
                versionName = pi.versionName;
                versionCode = pi.versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        CRASH_HEAD = "************* Crash Log Head ****************" +
                "\nDevice Manufacturer: " + Build.MANUFACTURER +// 设备厂商
                "\nDevice Model       : " + Build.MODEL +// 设备型号
                "\nAndroid Version    : " + Build.VERSION.RELEASE +// 系统版本
                "\nAndroid SDK        : " + Build.VERSION.SDK_INT +// SDK 版本
                "\nApp VersionName    : " + versionName +
                "\nApp VersionCode    : " + versionCode +
                "\n************* Crash Log Head ****************\n\n";
        if (isSpace(crashDir)) {
            dir = null;
        } else {
            dir = crashDir.endsWith(FILE_SEP) ? crashDir : crashDir + FILE_SEP;
        }
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                && context.getExternalCacheDir() != null)
            defaultDir = context.getExternalCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        else {
            defaultDir = context.getCacheDir() + FILE_SEP + "crash" + FILE_SEP;
        }
        Thread.setDefaultUncaughtExceptionHandler(UNCAUGHT_EXCEPTION_HANDLER);
    }

    private static boolean createOrExistsFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}