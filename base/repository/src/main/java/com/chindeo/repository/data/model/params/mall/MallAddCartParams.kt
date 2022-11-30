package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField

class MallAddCartParams(
        @JSONField(name = "productId")
        val productId: Int,
        @JSONField(name = "productAttrUnique")
        val productAttrUnique: String,
        @JSONField(name = "cartNum")
        val cartNum: Int,
        @JSONField(name = "IsNew")
        val IsNew: Int,
        @JSONField(name = "productType")
        var productType: Int = 1,
)