package com.chindeo.repository.data.model.response.device

/**
 * 智能家居，设备实体
 * Created by xiemaohui on 2022/7/5
 */
data class SmartDeviceBean(
    var id: Int? = null, //1,
    var code:String? = null,//"b827ebd51aa9",
    var name: String? = null,//"01灯",
    var number: String? = null,
    var region: String? = null,
    var type: Int = 0,//"0",  0:吊灯,1:净化器,2:空调,3:加湿器,4:音响,5:希沃电视,6:小米电视,7:窗帘,8遥控器
    var statusType: Int = 0,//"1",0:无状态,1:有状态
    var status: Int = 0,//"1", -1:离线状态,0:关闭状态,1:开启状态
    var locId: String? = null,
    var roomId :String? = null,
    var roomName: String? = null,
    var params: String? = null,
    var createTime: String? = null,
    var updateTime: String? = null,
    var select: Boolean=false
){
    override fun toString(): String {
        return "SmartDeviceBean(id=$id, code=$code, name=$name, number=$number, region=$region, 设备类型type=$type, 有无状态statusType=$statusType, 是否离线status=$status, locId=$locId, roomId=$roomId, roomName=$roomName, params=$params, createTime=$createTime, updateTime=$updateTime, select=$select)"
    }
}