package com.chindeo.repository.data.model.response.mall;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 根据id获取的订单详情
 * Created by xiemaohui on 2022/5/30
 */
public class MallOrderDetailBean {

    /**
     * 订单id
     */
    @JSONField(name = "id")
    public int id;
    /**
     *创建时间
     */
    @JSONField(name = "createdAt")
    public String createdAt;
    /**
     *更新时间
     */
    @JSONField(name = "updatedAt")
    public String updatedAt;
    /**
     *订单号
     */
    @JSONField(name = "orderSn")
    public String orderSn;
    /**
     *用户姓名
     */
    @JSONField(name = "realName")
    public String realName;
    /**
     *用户电话
     */
    @JSONField(name = "userPhone")
    public String userPhone;
    /**
     *用户地址
     */
    @JSONField(name = "userAddress")
    public String userAddress;
    /**
     *订单商品数量
     */
    @JSONField(name = "totalNum")
    public int totalNum;

    /**
     *订单商品总价
     */
    @JSONField(name = "totalPrice")
    public Double totalPrice;

    /**
     *订单邮费
     */
    @JSONField(name = "totalPostage")
    public Double totalPostage;

    /**
     *订单支付总价
     */
    @JSONField(name = "payPrice")
    public Double payPrice;

    /**
     *订单支付邮费
     */
    @JSONField(name = "payPostage")
    public Double payPostage;

    /**
     *平台手续费
     */
    @JSONField(name = "commissionRate")
    public Double commissionRate;

    /**
     *订单类型
     */
    @JSONField(name = "orderType")
    public int orderType;

    /**
     *支付状态 1支付，2未支付
     */
    @JSONField(name = "paid")
    public int paid;

    /**
     *支付时间
     */
    @JSONField(name = "payTime")
    public String payTime;

    /**
     *支付方式 1=微信 2=小程序 3=h5 4=余额 5=支付宝
     */
    @JSONField(name = "payType")
    public int payType;

    /**
     *订单状态：数据参考，订单状态对照接口
     */
    @JSONField(name = "status")
    public int status;

    /**
     *发货类型(0:未发货 1:发货 2: 送货 3: 虚拟)
     */
    @JSONField(name = "deliveryType")
    public int deliveryType;

    /**
     *快递名称/送货人姓名
     */
    @JSONField(name = "deliveryName")
    public String deliveryName;

    /**
     *快递单号/手机号
     */
    @JSONField(name = "deliveryId")
    public String deliveryId;

    /**
     *用户备注
     */
    @JSONField(name = "mark")
    public String mark;

    /**
     *商户备注
     */
    @JSONField(name = "remark")
    public String remark;

    /**
     *后台备注
     */
    @JSONField(name = "adminMark")
    public String adminMark;

    /**
     *核销码
     */
    @JSONField(name = "verifyCode")
    public String verifyCode;

    /**
     *核销时间
     */
    @JSONField(name = "verifyTime")
    public String verifyTime;

    /**
     *活动类型 1：普通 2:秒杀 3:预售 4:助力
     */
    @JSONField(name = "activityType")
    public int activityType;

    /**
     *成本价
     */
    @JSONField(name = "cost")
    public double cost;

    /**
     *是否取消 1.取消，2，未取消
     */
    @JSONField(name = "isCancel")
    public int isCancel;

    /**
     *后台是否删除
     */
    @JSONField(name = "isSystemDel")
    public int isSystemDel;


    @JSONField(name = "sysUserId")
    public int sysUserId;

    @JSONField(name = "sysTenancyId")
    public int sysTenancyId;

    @JSONField(name = "groupOrderId")
    public int groupOrderId;

    /**
     *对账id
     */
    @JSONField(name = "reconciliationId")
    public int reconciliationId;

    /**
     *用户昵称
     */
    @JSONField(name = " userNickName")
    public String  userNickName;

    @JSONField(name = "orderProduct")
    public List<MallOrderListBean.OrderProductBean> orderProduct;

    @JSONField(name = "orderStatuses")
    public List<orderStatusesBean> orderStatuses;


    public static class orderStatusesBean{
        @JSONField(name = "changeType")
        public String changeType;

        @JSONField(name = "changeMessage")
        public String changeMessage;

        @JSONField(name = "changeTime")
        public String changeTime;

        @JSONField(name = "orderId")
        public int orderId;
    }
}
