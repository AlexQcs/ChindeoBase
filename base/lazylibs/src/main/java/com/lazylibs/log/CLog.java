package com.lazylibs.log;

import android.util.Log;

import androidx.annotation.IntDef;


import com.orhanobut.logger.BuildConfig;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 */

public final class CLog {

    public static final int VERBOSE = Log.VERBOSE;
    public static final int DEBUG = Log.DEBUG;
    public static final int INFO = Log.INFO;
    public static final int WARN = Log.WARN;
    public static final int ERROR = Log.ERROR;
    public static final int ASSERT = Log.ASSERT;

    @IntDef({VERBOSE, DEBUG, INFO, WARN, ERROR, ASSERT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Level {
    }

    private static ICLogPrinter printer = new CLogPrinter();

    private static boolean isLoggable = BuildConfig.DEBUG;

    static boolean isLoggable() {
        return isLoggable;
    }

    public static void setLoggable(boolean isLogger) {
        CLog.isLoggable = isLogger;
    }

    public static void printer(ICLogPrinter printer) {
        CLog.printer = printer;
    }

    public static ICLogPrinter t(String tag) {
        return printer.t(tag);
    }

    public static void d(String message, Object... args) {
        printer.d(message, args);
    }

    public static void d(Object object) {
        printer.d(object);
    }

    public static void e(String message, Object... args) {
        printer.e(message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        printer.e(throwable, message, args);
    }

    public static void w(String message, Object... args) {
        printer.w(message, args);
    }

    public static void i(String message, Object... args) {
        printer.i(message, args);
    }

    public static void v(String message, Object... args) {
        printer.v(message, args);
    }

    public static void wtf(String message, Object... args) {
        printer.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     *
     * @param json json string content
     */
    public static void json(String json) {
        printer.json(json);
    }

    /**
     * Formats the given xml content and print it
     *
     * @param xml xml string content
     */
    public static void xml(String xml) {
        printer.xml(xml);
    }

    public static void log(@Level int priority, String tag, String message, Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

}
