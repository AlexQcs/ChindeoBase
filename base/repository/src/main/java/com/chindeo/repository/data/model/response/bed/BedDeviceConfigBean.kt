package com.chindeo.repository.data.model.response.bed


import com.alibaba.fastjson.annotation.JSONField
import androidx.annotation.Keep
import com.chindeo.repository.data.model.nurses.DeviceUserBean

@Keep
data class BedDeviceConfigBean(
    val chat: List<String> = listOf(),
    var chindeoQRCode: String? = "", // http://10.0.0.23/upload/file/1592906093262.png
    var chindeoQRCodeName: String? = "", // 智慧病房
    val device: DeviceBean = DeviceBean(),
    val deviceUser: DeviceUserBean? = null,
    val dinnerQRCode: String? = "", // http://10.0.0.23/upload/file/1609149961677.png
    @JSONField(name = "dinnerQRCodeName")
    val qrName: String? = "", // 17床
    val dinning: HostConfig? = null,
    val dinningUrl: String? = null,
    var hostGroupUsers: List<HostUserBean>? = null, //v3音视频拨打号码列表
    val ftp: FtpBean = FtpBean(),
    val hospitalQRCode: String? = "", // http://10.0.0.23/upload/file/1609313408259.png
    val hospitalQRCodeName: String? = "", // 走进医院
    val hospitalType: String? = "", // 0
    val hostUser: HostUserBean? = null,
    val loc: LocBean = LocBean(),
    val webVersion: String? = "", // 1.0.0
    val emrType: String? = "img", // 病例类型 img html pdf
    val carer: HostConfig? = null,
    val mall: HostConfig? = null,
) {
    @Keep
    data class DeviceBean(
            val bedId: String = "",
            val sleepTime: String = "",
            val startUpTime: String = ""
    )

    @Keep
    data class DeviceUserBean(
            val deviceIp: String = "", // 10.0.0.23
            val displayName: String = "", // WAN-dennis
            val password: String = "", // Chindeo
            val userName: String = "", // 5024
    )

    @Keep
    data class HostConfig(
            val domain: String = "", // https://order.chindeo.com
            val id: String = "", // d6094465156103af3048b59d9ee2ec
            val secret: String = "", // b8b8f5faa7e6e0debff0e209d2360bd510b37ba58fe08dd3e4ba78c9f0de99e7
            val uuid: String = ""
    )

    @Keep
    data class FtpBean(
            val frequency: Int = 0, // 5
            val host: String = "", // 10.0.0.23
            val password: String = "", // Chindeo
            val port: String = "", // 21
            val username: String = "" // admin
    )

    // 主机配置
    @Keep
    data class HostUserBean(
            val deviceIp: String? = "", // 10.0.0.23
            val displayName: String? = "", // 徐文退回心血管内科病区
            val password: String? = "", // Chindeo
            val userName: String? = "", // 1021
            val music: String? = "",//铃声序号 null 0 表示 默认，
            val autoAnswer: String? ="", //0不开启自动接听，1开启自动接听
            var id: String? = "",
            var phoneNumber: String? = ""
    )

    @Keep
    data class LocBean(
            val logo: String = "", // http://10.0.0.23/static/image/logo.png
            val name: String = "" // 心血管内科病区
    )

    override fun toString(): String {
        return "BedDeviceConfigBean(chat=$chat, chindeoQRCode='$chindeoQRCode', chindeoQRCodeName='$chindeoQRCodeName', device=$device, deviceUser=$deviceUser, dinnerQRCode='$dinnerQRCode', dinnerQRCodeName='$qrName', dinning=$dinning, ftp=$ftp, hospitalQRCode='$hospitalQRCode', hospitalQRCodeName='$hospitalQRCodeName', hospitalType='$hospitalType', hostUser=$hostUser, loc=$loc, webVersion='$webVersion')"
    }


}