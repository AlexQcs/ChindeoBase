package com.chindeo.repository.data.model.response.mall;

public enum MallPayChannel {

    WECHAT("微信", "1"),

    APPLETS("小程序", "2"),

    H5("h5", "3"),

    Balance("余额", "4"),

    ALIPAY("支付宝", "5"),

    BANK("银行", "6");

    public final String name;
    public final String code;

    MallPayChannel(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
