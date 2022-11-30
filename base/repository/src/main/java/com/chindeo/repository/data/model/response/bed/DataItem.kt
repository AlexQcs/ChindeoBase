package com.chindeo.repository.data.model.response.bed

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class DataItem(
    @SerializedName("menuOrder")
    val menuOrder: Int = 0,
    @SerializedName("createTime")
    val createTime: String = "",
    @SerializedName("updateTime")
    val updateTime: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("href")
    val href: String = "",
    @SerializedName("menu")
    val menu: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("contentType")
    val contentType: String = "",
    @SerializedName("content")
    val content: String = "",
    @SerializedName("isSelect")
    var isSelect: String = ""): Parcelable