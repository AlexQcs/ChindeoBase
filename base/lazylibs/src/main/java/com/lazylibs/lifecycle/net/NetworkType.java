package com.lazylibs.lifecycle.net;


public enum NetworkType {

    CONNECT("network connect"),
    DIS_CONNECT("network disconnect");

    private String desc;

    NetworkType(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }

}