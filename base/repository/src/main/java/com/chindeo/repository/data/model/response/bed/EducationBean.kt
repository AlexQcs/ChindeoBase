package com.chindeo.repository.data.model.response.bed


import com.alibaba.fastjson.annotation.JSONField
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class EducationBean(
    @JSONField(name = "classItems")
    var classItems: List<ClassItem>,
    @JSONField(name = "className")
    var className: String
) : Parcelable {
    @Keep
    @Parcelize
    data class ClassItem(
        @JSONField(name = "click")
        var click: Int?,
        @JSONField(name = "code")
        var code: String?,
        @JSONField(name = "createDate")
        var createDate: String?,
        @JSONField(name = "id")
        var id: Int?,
        @JSONField(name = "item")
        var item: String?,
        @JSONField(name = "itemCover")
        var itemCover: String?,
        @JSONField(name = "itemType")
        var itemType: String?,
        @JSONField(name = "itemUrl")
        var itemUrl: String?,
        @JSONField(name = "updateDate")
        var updateDate: String?,
        @JSONField(name = "viewerUrl")
        var viewerUrl: String?,
    ) : Parcelable
}