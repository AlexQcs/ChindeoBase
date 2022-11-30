package com.chindeo.repository.data.model.response.arcitem

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PatientJianChaBean(
//        val exSeriNo: String?,      // "1389464",
//        val locName: String?,       // "放射科",
//        val itemNo: String?,        // 医嘱号",
//        val id: String?,        // 99,
//        val applyDate: String?,     // "2019-01-27",
//        val applyTime: String?,     // "09:08:00",
//        val arcItem: String?,       // "胸部正位侧位片(DR)",
//        val status: String?          // "已写报告"
@SerializedName("authDate")
val authDate: String = "",
@SerializedName("locName")
val locName: String = "",
@SerializedName("authTime")
val authTime: String = "",
@SerializedName("itemNo")
val itemNo: String = "",
@SerializedName("resultDesc")
val resultDesc: String = "",
@SerializedName("exSeriNo")
val exSeriNo: String = "",
@SerializedName("examDesc")
val examDesc: String = "",
@SerializedName("id")
val id: Int = 0,
@SerializedName("applyDate")
val applyDate: String = "",
@SerializedName("applyTime")
val applyTime: String = "",
@SerializedName("arcItem")
val arcItem: String = "",
@SerializedName("statusCode")
val statusCode: String = "",
@SerializedName("status")
val status: String = "",

)
