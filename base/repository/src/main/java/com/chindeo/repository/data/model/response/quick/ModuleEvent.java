package com.chindeo.repository.data.model.response.quick;

/**
 * 模块通信的数据模型定义
 */
public class ModuleEvent<T> {
    public ModuleEvent() {
    }

    public ModuleEvent(int what) {
        this.what = what;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public T getData() {
        return t;
    }

    public void setData(T t) {
        this.t = t;
    }

    private int what;
    private T t;
}
