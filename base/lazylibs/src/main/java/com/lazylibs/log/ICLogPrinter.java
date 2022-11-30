package com.lazylibs.log;

/**
 */

public interface ICLogPrinter {

    ICLogPrinter t(String tag);

    void d(String message, Object... args);

    void d(Object object);

    void e(String message, Object... args);

    void e(Throwable throwable, String message, Object... args);

    void w(String message, Object... args);

    void i(String message, Object... args);

    void v(String message, Object... args);

    void wtf(String message, Object... args);

    /**
     * Formats the given json content and print it
     *
     * @param json json string content
     */
    void json(String json);

    /**
     * Formats the given xml content and print it
     *
     * @param xml xml string content
     */
    void xml(String xml);

    void log(@CLog.Level int priority, String tag, String message, Throwable throwable);

}
