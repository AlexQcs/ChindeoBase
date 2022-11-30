package com.chindeo.repository.util;

import android.util.Log;


import com.apkfuns.logutils.LogLevel;
import com.apkfuns.logutils.LogUtils;
import com.apkfuns.logutils.file.LogFileEngine;
import com.apkfuns.logutils.file.LogFileParam;
import com.chindeo.repository.contants.FilePathConstants;
import com.lazylibs.http.data.flowable.ResultFlowableTransformer;
import com.lazylibs.util.TimeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class LoggerUtil {
    private static String fileName = "appName";
    private static final int saveDay = 3;

    private static String getLoggerPath() {
        return FilePathConstants.getDir(FilePathConstants.APP_LOG_DIR);
    }

    private static String getLoggerFileName() {
        return TimeUtils.getTodayDate() + "_" + fileName;
    }

    //初始化方法，传入文件夹、文件名，即可实现把日志写入磁盘
    public static void init(String appName) {
        Log.i(LoggerUtil.class.getSimpleName(), "LoggerUtil init appName->" + appName);
        fileName = appName;

        clearOldLog(saveDay);

        LogUtils.getLog2FileConfig().configLog2FileEnable(true);//是否支持写入文件
        LogUtils.getLog2FileConfig().configLog2FilePath(getLoggerPath());//写入日志路径
        LogUtils.getLog2FileConfig().configLog2FileNameFormat(getLoggerFileName());//写入日志文件名 %d{yyyyMMdd}.txt
        //设置引擎
        LogUtils.getLog2FileConfig().configLogFileEngine(new LogFileEngine() {
            @Override
            public void writeToFile(File logFile, String logContent, LogFileParam params) {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String levelStr = "I";
                switch (params.getLogLevel()) {
                    case LogLevel.TYPE_VERBOSE:
                        levelStr = "V";
                        break;
                    case LogLevel.TYPE_INFO:
                        levelStr = "I";
                        break;
                    case LogLevel.TYPE_DEBUG:
                        levelStr = "D";
                        break;
                    case LogLevel.TYPE_WARM:
                        levelStr = "W";
                        break;
                    case LogLevel.TYPE_ERROR:
                        levelStr = "E";
                        break;
                    case LogLevel.TYPE_WTF:
                        levelStr = "WTF";
                        break;
                }
                StringBuffer logResult = new StringBuffer();
                if (logContent.length() > 2000) {
                    logResult.append(logContent.substring(0, 2000));
                    logResult.append(" ... 省略输出 >").append(logContent.length() - 2000).append(" 个字");
                } else {
                    logResult.append(logContent);
                }
                String data = "[" + sdf.format(params.getTime()) + "] " + levelStr + "/ " + logResult;
                appendData(getLoggerPath() + getLoggerFileName(), data);

//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        String data = "["+sdf.format(params.getTime()) + "] " + logContent;
//                        appendData(logFile.getPath(), data);
//                    }
//                }).start();

            }

            @Override
            public void flushAsync() {
                Log.e(LoggerUtil.class.getSimpleName(), "flushAsync");

            }

            @Override
            public void release() {
                Log.e(LoggerUtil.class.getSimpleName(), "release");

            }
        });

//        demo
//        LogUtils.getLogConfig()
//                .configAllowLog(true)
//                .configTagPrefix("MyAppName")
//                .configShowBorders(true)
//                .configFormatTag("%d{HH:mm:ss:SSS} %t %c{-5}")
//
//        //支持写入日志到文件
//        LogUtils.getLog2FileConfig().configLog2FileEnable(true)
//                // targetSdkVersion >= 23 需要确保有写sdcard权限
//                .configLog2FilePath("/sdcard/项目文件夹/logs/")
//                .configLog2FileNameFormat("%d{yyyyMMdd}.txt")
//                .configLogFileEngine(new LogFileEngineFactory());

//           sample
//        // 输出字符串
//        LogUtils.d("12345");
//
//// 输出参数
//        LogUtils.d("12%s3%d45", "a", 0);
//
//// 输出异常
//        LogUtils.d(new NullPointerException("12345"));
//
//// 输出对象
//        Person person = new Person();
//        person.setAge(11);
//        person.setName("pengwei");
//        person.setScore(37.5f);
//        LogUtils.d(person);
//
//// 对象为空
//        LogUtils.d(null);
//
//// 输出json（json默认debug打印）
//        String json = "{'a':'b','c':{'aa':234,'dd':{'az':12}}}";
//        LogUtils.json(json);
//
//// 打印数据集合
//        List<Person> list1 = new ArrayList<>();
//        for(int i = 0; i < 4; i++){
//            list1.add(person);
//        }
//        LogUtils.d(list1);
//
//// 打印数组
//        double[][] doubles = {{1.2, 1.6, 1.7, 30, 33},
//                {1.2, 1.6, 1.7, 30, 33},
//                {1.2, 1.6, 1.7, 30, 33},
//                {1.2, 1.6, 1.7, 30, 33}};
//        LogUtils.d(doubles);
//
//// 自定义tag
//        LogUtils.tag("我是自定义tag").d("我是打印内容");
//
//// 其他用法
//        LogUtils.v("12345");
//        LogUtils.i("12345");
//        LogUtils.w("12345");
//        LogUtils.e("12345");
//        LogUtils.wtf("12345");
    }

    private static void clearOldLog(int beforeDay) {
        File dirFile = new File(getLoggerPath());
        if (dirFile.exists()) {
            File[] files = dirFile.listFiles();
            if (files!=null && files.length>0) {
                for (int i = 0; i < files.length; i++) {
                    File targetFile = files[i];
                    if (targetFile.isFile()) {
                        String fileName = targetFile.getName();
                        if (fileName.contains("_")) {
                            String createDate = fileName.substring(0, fileName.indexOf("_"));
                            long days = getDistDates(new Date(), TimeUtils.string2Date(createDate, TimeUtils.FORMAT_YYYY_MM_DD));
                            if (days >= beforeDay) {
                                Log.i(LoggerUtil.class.getSimpleName(), "#大于删除日志文件->" + targetFile);
                                targetFile.delete();
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 在文件中添加数据
     *
     * @param filePath 文件路径
     * @param data     添加数据
     * @return
     */
    private static boolean appendData(String filePath, String data) {
        FileOutputStream out = null;
        try {
            File file = createFile(filePath);
            //创建文件输入流
            out = new FileOutputStream(file, true);              //如果追加方式用true
            //写入内容
            out.write((data + "\n").getBytes("utf-8"));      //注意需要转换对应的字符集
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (out != null) {
                    out.close();   //关闭流
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    //创建文件
    private static File createFile(String filePath) {
        //目标文件
        File file = new File(filePath);
        //若不存在即创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {   //如果父文件夹不存在
                file.getParentFile().mkdirs();      //新建多层文件夹
            }
            Log.d("LoggerUtil", "创建文件" + filePath);
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    //创建文件夹 、 目录
    private static void createFolder(String path) {
        File myPath = new File(path);
        if (!myPath.exists()) {                     //若此目录不存在，则创建之
            myPath.mkdir();
        }
    }

    public static long getDistDates(Date startDate, Date endDate) {
        long totalDate = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        long timestart = calendar.getTimeInMillis();
        calendar.setTime(endDate);
        long timeend = calendar.getTimeInMillis();
        totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);
        return totalDate;
    }

}
