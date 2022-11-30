package com.chindeo.repository.data.model.response.nursedoc

import com.google.gson.annotations.SerializedName

data class CheckResultBean(@SerializedName("acceptDate")
                           val acceptDate: String = "",
                           @SerializedName("authDate")
                           val authDate: String = "",
                           @SerializedName("acceptTime")
                           val acceptTime: String = "",
                           @SerializedName("labName")
                           val labName: String = "",
                           @SerializedName("authTime")
                           val authTime: String = "",
                           @SerializedName("abn")
                           val abn: Int = 0,
                           @SerializedName("labNo")
                           val labNo: String = "",
                           @SerializedName("collTime")
                           val collTime: String = "",
                           @SerializedName("labRepNo")
                           val labRepNo: String = "",
                           @SerializedName("id")
                           val id: Int = 0,
                           @SerializedName("applyDate")
                           val applyDate: String = "",
                           @SerializedName("applyTime")
                           val applyTime: String = "",
                           @SerializedName("arcItem")
                           val arcItem: String = "",
                           @SerializedName("status")
                           val status: String = "",
                           @SerializedName("statusCode")
                           val statusCode: String = "",
                           @SerializedName("collDate")
                           val collDate: String = "")