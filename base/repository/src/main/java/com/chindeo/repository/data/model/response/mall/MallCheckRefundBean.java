package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MallCheckRefundBean {

    @JSONField(name = "totalRefundPrice")
    public String totalRefundPrice;
    @JSONField(name = "postagePrice")
    public String postagePrice;
    @JSONField(name = "product")
    public List<ProductBean> product;
    @JSONField(name = "status")
    public int status;

    public static class ProductBean {
        @JSONField(name = "id")
        public int id;
        @JSONField(name = "cartInfo")
        public MallCartInfoBean cartInfo;
        @JSONField(name = "productSku")
        public String productSku;
        @JSONField(name = "unique")
        public String unique;
        @JSONField(name = "isRefund")
        public int isRefund;
        @JSONField(name = "productNum")
        public int productNum;
        @JSONField(name = "productType")
        public String productType;
        @JSONField(name = "refundNum")
        public int refundNum;
        @JSONField(name = "isReply")
        public int isReply;
        @JSONField(name = "productPrice")
        public int productPrice;
        @JSONField(name = "orderID")
        public int orderID;
        @JSONField(name = "productId")
        public int productId;
    }
}
