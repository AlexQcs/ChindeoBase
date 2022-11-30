package com.chindeo.repository.data.model.call;

public class AppCallVo {
    //麦克风增强
    public Boolean micBoost = true;
    // 启用无人托管
    public Boolean isTrust = false;
    //护士正在护理中，替换超时的“无人接听”的提示语
    public String noResponse;
    //朗读的次数
    public int repeat;

    public AppCallVo() {
    }

    /**
     * @param micBoost   麦克风增强
     * @param noResponse 护士正在护理中，替换超时的“无人接听”的提示语
     */
    public AppCallVo(Boolean micBoost, String noResponse) {
        this.micBoost = micBoost;
        this.noResponse = noResponse;
    }
}
