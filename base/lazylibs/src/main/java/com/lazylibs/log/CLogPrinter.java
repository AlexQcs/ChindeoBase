package com.lazylibs.log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

public class CLogPrinter implements ICLogPrinter {

    static {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodOffset(2)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return CLog.isLoggable();
            }
        });
    }

    @Override
    public ICLogPrinter t(String tag) {
        if (tag != null) {
            Logger.t(tag);
        }
        return this;
    }

    @Override
    public void d(String message, Object... args) {
        Logger.d(message, args);
    }

    @Override
    public void d(Object object) {
        Logger.d(object);
    }

    @Override
    public void e(String message, Object... args) {
        Logger.e(message, args);
    }

    @Override
    public void e(Throwable throwable, String message, Object... args) {
        Logger.e(throwable, message, args);
    }

    @Override
    public void w(String message, Object... args) {
        Logger.w(message, args);
    }

    @Override
    public void i(String message, Object... args) {
        Logger.i(message, args);
    }

    @Override
    public void v(String message, Object... args) {
        Logger.v(message, args);
    }

    @Override
    public void wtf(String message, Object... args) {
        Logger.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     *
     * @param json json string content
     */
    @Override
    public void json(String json) {
        Logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     *
     * @param xml xml string content
     */
    @Override
    public void xml(String xml) {
        Logger.xml(xml);
    }

    @Override
    public void log(@CLog.Level int priority, String tag, String message, Throwable throwable) {
        Logger.log(priority, tag, message, throwable);
    }

}
