package com.chindeo.repository.data.model.response.bed


import androidx.annotation.Keep

@Keep
data class BedQueryPriceListBean(
        var id: Int? = null,
        var no: String? = null,
        var code: String? = null,
        var name: String? = null,
        var price: String? = null,
        var uom: String? = null,
        var itemSpec: String? = null,
        var itemCate: String? = null,
        var chargeBasic: String? = null,
        var activeFlag: String? = null,
        var itemCateSub: String? = null,
        var insuCode: String? = null,
        var insuName: String? = null,
)