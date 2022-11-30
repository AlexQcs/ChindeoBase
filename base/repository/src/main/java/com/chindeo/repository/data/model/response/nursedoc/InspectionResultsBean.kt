package com.chindeo.repository.data.model.response.nursedoc

import com.google.gson.annotations.SerializedName

data class InspectionResultsBean(
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
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""


)