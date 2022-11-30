package com.lazylibs.event;

/**
 * @param <T> 时间总线 传递类
 */
public class Event<T> {
    private String code;
    private T data;
    private T extend;

    public Event(String code, T data) {
        this.code = code;
        this.data = data;
    }
    public Event(String code, T data,T extend) {
        this.code = code;
        this.data = data;
        this.extend = extend;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getExtend() {
        return extend;
    }

    public void setExtend(T extend) {
        this.extend = extend;
    }
}
