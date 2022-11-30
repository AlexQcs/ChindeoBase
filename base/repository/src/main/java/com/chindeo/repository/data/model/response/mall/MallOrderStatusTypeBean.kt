package com.chindeo.repository.data.model.response.mall

/**
 * 订单状态
 * Created by xiemaohui on 2022/7/28
 */
data class MallOrderStatusTypeBean(
    var label:String?=null ,//全部 待付款 已付款 待提货 已收货 已完成 已取消
    var value:String?=null, // 0~6
    var size:Int =0 //
)