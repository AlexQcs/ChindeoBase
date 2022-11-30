package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 退款订单状态
 * Created by xiemaohui on 2022/5/30
 */
public class MallRefundStatusBean {
    @JSONField(name = "label")
    public List<LabelBean> label;
    @JSONField(name = "value")
    public String value;

    public static class LabelBean {
        @JSONField(name = "1")
        public String one;

        @JSONField(name = "2")
        public String two;
    }
}
