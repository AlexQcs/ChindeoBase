package com.chindeo.repository.data.model.params.nurses

/**
 * 保存主机托管
 * Created by xiemaohui on 2022/8/15
 */
data class HostTrusteeShipParams(
    var devDeputyId: Int,//副机设备id
    var devPrimaryId: Int, //主机设备id
    var devTrusteeshipId: Int?=0, //托管主机设备id
    var trusteeship: Int=0, //是否开启托管，0：否，1：是
    var trusteeshipTime: String? //托管时间HH:mm:ss
)

//{
//    "devTrusteeshipId": 195,
//    "trusteeship": 0,
//    "devPrimaryId": 195,
//    "devDeputyId": 195,
//    "trusteeshipTime": "06:00:00"
//}