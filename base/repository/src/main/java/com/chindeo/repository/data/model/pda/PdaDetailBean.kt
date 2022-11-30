package com.chindeo.repository.data.model.pda

import androidx.annotation.Keep
import com.chindeo.repository.data.model.response.bed.BedDeviceConfigBean
import com.chindeo.repository.data.model.response.bed.EventsBean

@Keep
data class PdaDetailBean(
    var locCode: String,
    val deviceUser: DeviceUSerBean,
    val ftp: FtpBean,
    val chat: List<String> = listOf()
) {
    @Keep
    data class DeviceUSerBean(
        var id: String="",//"10",
        var userName: String="",//"1112",
        var password: String="",//"Chindeo",
        var displayName: String="",//"护士站主机"
        var deviceIp: String="",//"10.0.0.25"
        var autoAnswer: String="",//0不开启自动接听，1开启自动接听
        var music: String="",//铃声序号 null 0 表示 默认，
    )

    @Keep
    data class FtpBean(
        var password: String="",//"Chindeo",
        var port: String="",//"21",
        var host: String="",//"10.0.0.23",
        var username: String="",//"admin"
    )

}


