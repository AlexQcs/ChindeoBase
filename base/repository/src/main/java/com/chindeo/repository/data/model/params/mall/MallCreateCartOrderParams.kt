package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField

/**
 * 结算购物车
 * Created by xiemaohui on 2022/5/13
 */
class MallCreateCartOrderParams(
        @JSONField(name = "cartIds")
        val cartIds: List<Int>,
        @JSONField(name = "orderType")
        var orderType: Int = 1,
        @JSONField(name = "remark")
        val remark: String,
)