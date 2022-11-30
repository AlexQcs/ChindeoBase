package com.chindeo.repository.data.model.params.bed

import com.alibaba.fastjson.annotation.JSONField

data class BedObHealthParams(
        val admId: String,
        val observationIds: List<Int>
)