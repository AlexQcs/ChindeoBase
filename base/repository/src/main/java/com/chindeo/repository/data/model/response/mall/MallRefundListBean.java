package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 退款订单列表
 */
public class MallRefundListBean {


    @JSONField(name = "list")
    public List<ListBean> list;
    @JSONField(name = "page")
    public int page;
    @JSONField(name = "pageSize")
    public int pageSize;
    @JSONField(name = "stat")
    public StatBean stat;
    @JSONField(name = "total")
    public int total;

    public static class StatBean {
        @JSONField(name = "agree")
        public int agree;
        @JSONField(name = "all")
        public int all;
        @JSONField(name = "audit")
        public int audit;
        @JSONField(name = "backgood")
        public int backgood;
        @JSONField(name = "end")
        public int end;
        @JSONField(name = "refuse")
        public int refuse;
    }

    public static class ListBean {
        @JSONField(name = "id")
        public String id;
        @JSONField(name = "createdAt")
        public String createdAt;
        @JSONField(name = "updatedAt")
        public String updatedAt;
        @JSONField(name = "refundOrderSn")
        public String refundOrderSn;
        @JSONField(name = "deliveryType")
        public String deliveryType;
        @JSONField(name = "deliveryId")
        public String deliveryId;
        @JSONField(name = "deliveryMark")
        public String deliveryMark;
        @JSONField(name = "deliveryPics")
        public String deliveryPics;
        @JSONField(name = "deliveryPhone")
        public String deliveryPhone;
        @JSONField(name = "merDeliveryUser")
        public String merDeliveryUser;
        @JSONField(name = "merDeliveryAddress")
        public String merDeliveryAddress;
        @JSONField(name = "phone")
        public String phone;
        @JSONField(name = "mark")
        public String mark;
        @JSONField(name = "merMark")
        public String merMark;
        @JSONField(name = "adminMark")
        public String adminMark;
        @JSONField(name = "pics")
        public String pics;
        @JSONField(name = "refundType")
        public int refundType;
        @JSONField(name = "refundMessage")
        public String refundMessage;
        @JSONField(name = "refundPrice")
        public String refundPrice;
        @JSONField(name = "refundNum")
        public int refundNum;
        @JSONField(name = "failMessage")
        public String failMessage;
        @JSONField(name = "status")
        public int status;
        @JSONField(name = "statusTime")
        public String statusTime;
        @JSONField(name = "isCancel")
        public int isCancel;
        @JSONField(name = "isSystemDel")
        public int isSystemDel;
        @JSONField(name = "activityType")
        public int activityType;
        @JSONField(name = "orderSn")
        public String orderSn;
        @JSONField(name = "userNickName")
        public String userNickName;
        @JSONField(name = "tenancyName")
        public String tenancyName;
        @JSONField(name = "isTrader")
        public int isTrader;
        @JSONField(name = "reconciliationId")
        public int reconciliationId;
        @JSONField(name = "orderId")
        public int orderId;
        @JSONField(name = "cUserId")
        public int cUserId;
        @JSONField(name = "sysTenancyId")
        public int sysTenancyId;
        @JSONField(name = "refundProduct")
        public List<RefundProductBean> refundProduct;

        public static class RefundProductBean {
            @JSONField(name = "refundOrderId")
            public int refundOrderId;
            @JSONField(name = "orderProductId")
            public int orderProductId;
            @JSONField(name = "refundNum")
            public int refundNum;
            @JSONField(name = "id")
            public String id;
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
            public int productType;
            @JSONField(name = "isReply")
            public int isReply;
            @JSONField(name = "productPrice")
            public String productPrice;
            @JSONField(name = "orderID")
            public int orderID;
            @JSONField(name = "productId")
            public int productId;
        }

    }
}
