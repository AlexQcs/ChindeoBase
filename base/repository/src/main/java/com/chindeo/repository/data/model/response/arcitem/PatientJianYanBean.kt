package com.chindeo.repository.data.model.response.arcitem


import com.alibaba.fastjson.annotation.JSONField
import androidx.annotation.Keep

@Keep
data class PatientJianYanBean(
    var acceptDate: String? = null,
    var authDate: String? = null, // 2021-09-10
    var acceptTime: String? = null,
    var labName: String? = null, // 血清
    var authTime: String? = null, // 09:09:00
    var abn: Int? = null, // 1229545
    var labNo: String? = null, // 9133455295
    var collTime: String? = null, // 07:00:00
    var labRepNo: String? = null, // 8573073
    var id: Int? = null, // 407415
    var applyDate: String? = null, // 2021-09-09
    var applyTime: String? = null, // 16:19:28
    var arcItem: String? = null, // 肝功八项
    var status: String? = null, // 已发报告
    var statusCode: String? = null, // RP
    var collDate: String? = null // 2021-09-10
)