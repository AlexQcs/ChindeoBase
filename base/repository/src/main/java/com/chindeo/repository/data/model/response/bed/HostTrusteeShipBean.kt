package com.chindeo.repository.data.model.response.bed

/**
 * 主机托管信息及主机
 * Created by xiemaohui on 2022/8/12
 */

data class HostTrusteeShipBean(
    var devDeputyId: Int?=null,
    var devPrimaryId: Int?=null,
    var devTrusteeshipId: Int?=null,
    var deviceList: MutableList<DeviceListBean>?=null,
    var id: Int?=null,
    var locId: Int?=null,
    var trusteeship: String?=null,
    var trusteeshipTime: String?=null
){
    data class DeviceListBean(
    var code: String?=null,
    var desc: String?=null,
    var env: String?=null,
    var id: Int?=null,
    var position: String?=null,
    var sipUsersName: String?=null,
    var status: String?=null,
    var type: String?=null
)
}
