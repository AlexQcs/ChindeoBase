package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField

class MallCreateOrderParams(
        @JSONField(name = "cartIds")
        val cartIds: List<Int>,
        @JSONField(name = "orderType")
        var orderType: Int = 1,
        @JSONField(name = "remark")
        val remark: String,
        @JSONField(name = "addressId")
        val addressId: Int,
)