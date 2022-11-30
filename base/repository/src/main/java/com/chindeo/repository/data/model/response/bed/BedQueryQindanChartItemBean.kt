package com.chindeo.repository.data.model.response.bed
import androidx.annotation.Keep

@Keep
data class BedQueryQindanChartItemBean(
        var total: String,
        var details: List<DetailBean>? = null,
        var category: String
) {
    @Keep
    data class DetailBean(
            var admId: Int? = null,
            var itemName: String? = null,
            var itemCode: String? = null,
            var itemCharges: String? = null,
            var category: String? = null,
            var insuClass: String? = null,
            var insuRatio: String? = null,
            var qty: String? = null,
            var uom: String? = null,
            var price: String? = null,
            var totalAmt: String? = null,
            var billDate: String? = null,
            var billTime: String? = null,
            var itemNo: String? = null
    )
}