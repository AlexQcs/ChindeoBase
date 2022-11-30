package com.chindeo.repository.data.model.response.bed


import com.alibaba.fastjson.annotation.JSONField
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
@Parcelize
data class CareEducationBean(
    @JSONField(name = "admId")
    var admId: Int?,
    @JSONField(name = "locId")
    var locId: Int?,
    @JSONField(name = "norms")
    var norms: List<Norm?>?,
    @JSONField(name = "photo")
    var photo: String?,
    @JSONField(name = "submit")
    var submit: String?,
    @JSONField(name = "type")
    var type: String?,
    @JSONField(name = "userId")
    var userId: Int?,
    @JSONField(name = "userName")
    var userName: String?
) : Parcelable {
    @Keep
    @Parcelize
    data class Norm(
        @JSONField(name = "avg")
        var avg: Int?,
        @JSONField(name = "content")
        var content: String?,
        @JSONField(name = "normId")
        var normId: Int?,
        @JSONField(name = "normName")
        var normName: String?,
        @JSONField(name = "score")
        var score: Int?,
        @JSONField(name = "type")
        var type: String?
    ) : Parcelable
}