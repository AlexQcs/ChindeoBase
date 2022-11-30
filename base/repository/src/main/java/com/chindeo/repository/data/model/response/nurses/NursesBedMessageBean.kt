package com.chindeo.repository.data.model.response.nurses

/**
 * 床位消息提醒bean
 * Created by xiemaohui on 2022/8/22
 */
data class NursesBedMessageBean(
    var processed :AdditionalProperties?,
    var unprocessed :AdditionalProperties?
)

data class AdditionalProperties(
    var num: String?=null,
    var resultList: MutableList<ResultBean>?,
)

data class ResultBean(
    var id: Int,
    var list: MutableList<ListBean>?,
    var name: String?,
    var num: Int?,
    var sortTime: String?,
    var type: String?,
    var isRead:String? = "1"
)

data class ListBean(
    var code: String,
    var messages: MutableList<MessageBean>,
    var name: String,
    var num: Int,
    var sortTime: String,
)

data class MessageBean(
    var admId: Int,
    var code: String,
    var desc: String,
    var id: Int,
    var priority: String,
    var remindDate: String,
    var remindTime: String
)