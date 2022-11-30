package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class MallOrderListBean {


    @JSONField(name = "id")
    public int id;
    @JSONField(name = "createdAt")
    public String createdAt;
    @JSONField(name = "updatedAt")
    public String updatedAt;
    @JSONField(name = "orderSn")
    public String orderSn;
    @JSONField(name = "realName")
    public String realName;
    @JSONField(name = "userPhone")
    public String userPhone;
    @JSONField(name = "userAddress")
    public String userAddress;
    @JSONField(name = "totalNum")
    public int totalNum;
    @JSONField(name = "totalPrice")
    public String totalPrice;
    @JSONField(name = "totalPostage")
    public int totalPostage;
    @JSONField(name = "payPrice")
    public String payPrice;
    @JSONField(name = "payPostage")
    public int payPostage;
    @JSONField(name = "commissionRate")
    public int commissionRate;
    @JSONField(name = "orderType")
    public int orderType;//订单类型：1：普通，2：自提
    @JSONField(name = "paid")
    public int paid;
    @JSONField(name = "payTime")
    public String payTime;
    @JSONField(name = "payType")
    public int payType;
    @JSONField(name = "status")
    public int status; //参考状态对照表
    @JSONField(name = "deliveryType")
    public int deliveryType;
    @JSONField(name = "deliveryName")
    public String deliveryName;
    @JSONField(name = "deliveryId")
    public String deliveryId;
    @JSONField(name = "mark")
    public String mark;
    @JSONField(name = "remark")
    public String remark;
    @JSONField(name = "adminMark")
    public String adminMark;
    @JSONField(name = "activityType")
    public int activityType;
    @JSONField(name = "cost")
    public int cost;
    @JSONField(name = "isCancel")
    public int isCancel;//1.已取消 2.未取消
    @JSONField(name = "isSystemDel")
    public int isSystemDel;
    @JSONField(name = "groupOrderSn")
    public String groupOrderSn;
    @JSONField(name = "tenancyName")
    public String tenancyName;
    @JSONField(name = "isTrader")
    public int isTrader;
    @JSONField(name = "sysUserId")
    public int sysUserId;
    @JSONField(name = "sysTenancyId")
    public int sysTenancyId;
    @JSONField(name = "groupOrderId")
    public int groupOrderId;
    @JSONField(name = "reconciliationId")
    public int reconciliationId;
    @JSONField(name = "orderProduct")
    public List<OrderProductBean> orderProduct;

    @JSONField(name = "refundOrder")
    public RefundOrderBean refundOrder;

    public static class RefundOrderBean {

//
//                          "refundOrder": { // 关联退款单数据
//        "id": 4,  // 退款单id
//                "status": 2 // 退款单状态
//    },

        @JSONField(name = "id")
        public int refundId;

        @JSONField(name = "status")
        public String status;
    }


    public static class OrderProductBean {
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
        public int productType;
        @JSONField(name = "refundNum")
        public int refundNum;
        @JSONField(name = "isReply")
        public int isReply;
        @JSONField(name = "productPrice")
        public String productPrice;
        @JSONField(name = "orderId")
        public int orderID;
        @JSONField(name = "productId")
        public int productId;

    }

}
