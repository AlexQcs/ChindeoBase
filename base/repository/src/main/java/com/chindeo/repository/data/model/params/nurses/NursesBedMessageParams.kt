package com.chindeo.repository.data.model.params.nurses

/**
 *  床位消息已读params
 * Created by xiemaohui on 2022/8/22
 */
data class NursesBedMessageParams(
    var flag:String?=null,//标记,示例值(0)
    var id : Int?=null, //消息id msgNo
    var locCode :String?=null,//病区代码
    var mode : String?="0", //设置模式，0单消息设置，1按消息类型设置，2按优先级设置,示例值(1)
    var type : String?=null //消息类型 "crash"
)