package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by xiemaohui on 2022/5/23
 * 购物车商品总数
 */
public class MallCartNumBean {
    @JSONField(name = "total") //商品总数
    public String total;
}
