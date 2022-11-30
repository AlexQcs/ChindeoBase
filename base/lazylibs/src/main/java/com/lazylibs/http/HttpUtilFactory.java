package com.lazylibs.http;

import com.lazylibs.http.impl.IHttpUtil;

import java.util.HashMap;
import java.util.Map;

public class HttpUtilFactory {

    private static Map<Class, IHttpUtil> HTTP_MAP = new HashMap<>();

    public static <T extends IHttpUtil> T create(Class<T> cls) {
        //noinspection unchecked
        T data = (T) getHttpMap().get(cls);
        if (data == null) {
            try {
                data = cls.newInstance();
                getHttpMap().put(cls, data);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    private static Map<Class, IHttpUtil> getHttpMap() {
        if (HTTP_MAP == null) {
            HTTP_MAP = new HashMap<>();
        }
        return HTTP_MAP;
    }

    public static void clear() {
        HttpUtils.clear();
        HTTP_MAP.clear();
        HTTP_MAP = null;
    }

}
