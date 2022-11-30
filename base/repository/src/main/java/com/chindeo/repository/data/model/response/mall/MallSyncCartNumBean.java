package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 同步购物车数量bean
 * Created by xiemaohui on 2022/6/6
 */
public class MallSyncCartNumBean {
    /**
     * 商品id
     */
    public int id;
    /**
     * 购物车数量
     */
    public int cartNum;

    /**
     *  商品id数组
     */
    public int type;

    public List<Integer> ids;

    public MallSyncCartNumBean(int id, int cartNum,int type) {
        this.id = id;
        this.cartNum = cartNum;
        this.type=type;
    }

    public MallSyncCartNumBean(List<Integer> ids) {
        this.ids = ids;
    }
}
