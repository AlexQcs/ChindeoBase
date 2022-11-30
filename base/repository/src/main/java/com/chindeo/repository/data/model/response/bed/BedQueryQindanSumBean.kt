package com.chindeo.repository.data.model.response.bed


import androidx.annotation.Keep

@Keep
data class BedQueryQindanSumBean(
        val balance: String? = null,
        val deposit: String? = null,
        val sumFee: String? = null,
        val dateSums: List<QinDanDateItem>? = null
) {
    data class QinDanDateItem(
        val billSum: String,
        val billDate: String
        ) {

        var isChoose: Boolean = false
    }
}