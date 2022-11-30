package com.chindeo.repository.data.model.response.nurses

/**
 * 探视预约时间bean
 * Created by xiemaohui on 2022/8/26
 */
data class NursesVisitListBean(
    var adm_in_pat_no: String?="",//患者住院号
    var application_id: Int?=null,
    var bind_sip_users_name: String?="",
    var code: String?=null,//预约码
    var create_at: String?="",
    var ct_hospital_name: String?=null,//"深圳市宝安区人民医院",
    var dev_code: String?=null,
    var dev_desc: String?=null,//"演示床旁A4",
    var start_at: String?="",
    var end_at: String?="", //2022-10-10 16:21:37
    var id: Int?=null,
    var reject_msg:String?="", //反驳意见
    var loc_name: String?=null,
    var name: String?="",//"陈*丽",
    var pac_bed_desc: String?="",
    var pac_room_desc: String?="",
    var sip_users_name: String?=null,
    var status: String?=null,//状态 0:申请中,1:未开始,2:进行中,3:已完成,-1:已取消,-2:驳回预约
    var update_at: String?=null
)