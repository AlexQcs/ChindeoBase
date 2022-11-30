package com.chindeo.repository.data.model.response.nursedoc

/**
 * rows
 * Created by xiemaohui on 2022/6/23
 */
data class OrderExecutionInfoBean2(
    var cols:MutableList<OrderExecutionInfoBean.ColsBean>? = null,
    var rows :MutableList<Map<String, String>>?=null
     )