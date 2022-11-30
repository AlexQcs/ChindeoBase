package com.chindeo.repository.data.model.response.adm


data class AdmZhenliaoListBean(
    var date: String? = null, // 2019-01-20
    var items: List<ItemBean>? = null
) {
    data class ItemBean(
        var total: Int? = null, // 1
        var name: String? = null, // 碘佛醇注射液[74.1g：100ml]
        var subNo: String? = null, // 9249784||37
        var id: String? = null, // 4521
        var done: Boolean? = null, // 0
        var remark: String? = null, // 造影用　一次　74.1g　
        var startTime: String? = null, //开始时间 HH:mm
        var status: String? = null, // A 未完成 F 已完成
        var note: String?=null
    )
}