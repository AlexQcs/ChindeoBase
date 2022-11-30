package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by xiemaohui on 2022/5/23
 * 购物车商品列表
 */
public class MallCartListBean {
    @JSONField(name = "fails") //失效商品
    public List<MallCartProductBean> fails;

    @JSONField(name = "list")  //有效商品
    public  List<MallCartProductBean> list;

    @JSONField(name = "total") //商品总数
    public int total;

}
