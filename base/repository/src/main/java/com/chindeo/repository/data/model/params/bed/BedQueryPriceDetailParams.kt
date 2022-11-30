package com.chindeo.repository.data.model.params.bed

import com.alibaba.fastjson.annotation.JSONField

data class BedQueryPriceDetailParams(
        @JSONField(name = "cateSub")
        val categoryName: String,
        val abbrev: String,
)