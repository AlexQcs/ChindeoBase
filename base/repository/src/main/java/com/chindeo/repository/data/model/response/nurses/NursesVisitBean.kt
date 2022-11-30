package com.chindeo.repository.data.model.response.nurses

/**
 * 探视列表bean
 * Created by xiemaohui on 2022/9/20
 */
data class NursesVisitBean(
    var bedCode: String="",
    var callTime: String="",
    var devCode: String="",
    var room: String="",
    var name: String="",
    var numBer: String="",
    var timeStamp:String = "0"
)

data class RingtonesAccountsBean(var devIp:String, var ringtonFileName:String, var accounts:List<String>)

/**
 * bedGroupId 责任组id
 */
data class RingtonesGroup(var bedGroupId:String, var ringtonId:String, var ringName:String,var bedGroupDesc:String)

/**
 * type 铃声ID
 */
data class RingtonesFileByType(var name:String, var type:String,val filePath:String)

/**
 * fcid 铃声ID  id 责任组id
 */
data class BatchRingtonParams(var fcId:String, var id:String)




