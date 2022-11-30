package com.chindeo.repository.data.model.response.nurses


import com.alibaba.fastjson.annotation.JSONField
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Keep
data class NursesBroadcastTaskBean(
    @JSONField(name = "createDate")
    var createDate: String?,
    @JSONField(name = "id")
    var id: Int?,
    @JSONField(name = "locCode")
    var locCode: String?,
    @JSONField(name = "msg")
    var msg: String?,
    @JSONField(name = "remindTime")
    var remindTime: String?,
    @JSONField(name = "repeat")
    var repeat: String?,
    @JSONField(name = "topic")
    var topic: String?
)