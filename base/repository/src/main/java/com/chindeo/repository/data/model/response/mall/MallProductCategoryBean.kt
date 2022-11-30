package com.chindeo.repository.data.model.response.mall

import android.os.Parcel
import android.os.Parcelable
import com.alibaba.fastjson.annotation.JSONField
import com.chindeo.repository.data.model.response.mall.MallProductCategoryBean
import kotlinx.parcelize.Parcelize

@Parcelize
data class MallProductCategoryBean (
    var id: Int,
    var createdAt: String?=null,
    var updatedAt: String?=null,
    var pid: String?=null,
    var cateName: String?=null,
    var path: String?=null,
    var sort: String?=null,
    var pic: String?=null,
    var status: String?=null,
    var level: String?=null,
    var children: List<MallProductCategoryBean>?=null,
) : Parcelable