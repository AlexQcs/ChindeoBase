package com.chindeo.repository.data.model.response.nurses

/**
 * 呼叫主机bean
 * Created by xiemaohui on 2022/10/14
 */
data class NursesCallHostBean(
    var autoAnswer: String?="",//是否自动接听，0:否，1:是
    var bedId: Int?=null,
    var bindChat: String?="",    //绑定对应病区聊天
    var bindId: Int?=null,           //绑定设备ID
    var code: String?="",       //设备代码
    var createTime: String?="",
    var desc: String?="",
    var env: String?="",        //环境
    var id: Int?=null,
    var ipAddr: String?="",     //设备IP地址
    var isActive: String?="",   //设备激活状态
    var lnkDesignId: Int?=null,       //墨水屏模板ID
    var locDesc: String?="",    //病区
    var locId: Int?=null,
    var music: String?="",     //铃声
    var position: String?="",
    var roomId: Int?=null,
    var route: String?="",
    var sipUsersName: String?="", //freeswitch数据库的,sip_users表的name字段
    var sleepTime: String?="",
    var softwareId: Int?=null,       //软件ID
    var startUpTime: String?="",
    var status: String?="",     //设备状态，0:离线，1:在线，2:异常
    var type: String?="", //1:护理白板，2:床旁屏，3:门旁屏，4:护士站主机，5:通用WebApp，6:墨水屏，9:输液泵，10:PDA，11:网关，12:院内探视，13:安卓白板，14:走廊屏，15:输液监控仪，16:床垫，17:自发电按钮，18:X96Max+
    var use: String?="",  //设备用途，0:正式，1:自测
    var videoStatus: String?="",  //是否可视频状态，0:否，1:是
    var wardCode: Int?=null  //设备病区对应码
)