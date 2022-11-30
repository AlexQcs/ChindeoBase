package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField



/**
 * 订单列表请求参数
 * Created by xiemaohui on 2022/5/27
 */
class MallOrderBillListParams(
        @JSONField(name = "status") //订单状态
        val status: String,

        @JSONField(name = "date") //日期：today，yesterday，lately7，lately30，month，year或者日期范围:2021/08/01-2021/08/05
        val date: String,
//
//        @JSONField(name = "orderType") // 订单类型
//        val orderType: Int,
//
//        @JSONField(name = "sysTenancyId") // 订单类型
//        val sysTenancyId: Int,
//
//        @JSONField(name = "cUserId") // 用户id
//        val cUserId: Int,
//
//        @JSONField(name = "isTrader") // 是否自营
//        val isTrader: Int,
//
//        @JSONField(name = "keywords") // 订单号，用户名，手机号模糊搜索
//        val keywords: String,
//
//        @JSONField(name = "orderSn") // 订单号
//        val orderSn: String,
//
//        @JSONField(name = "username") // 用户名
//        val username: String,
//
//        @JSONField(name = "activityType") // 活动类型
//        val activityType: Int,
//
//        @JSONField(name = "reconciliationType") // 对账类型：1已对账，2未对账
//        val reconciliationType: Int,
//
//        @JSONField(name = "exceptIds") // 排除订单id
//        val exceptIds: IntArray,
)