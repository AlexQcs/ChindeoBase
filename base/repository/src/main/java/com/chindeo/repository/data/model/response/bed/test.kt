package com.chindeo.repository.data.model.response.bed
import androidx.annotation.Keep

import com.alibaba.fastjson.annotation.JSONField


@Keep
data class testBean(
    var code: Int = 0, // 200
    var message: String = "", // 操作成功
    var `data`: DataBean = DataBean()
) {
    @Keep
    data class DataBean(
        var bedCode: String = "", // 24
        var locCode: String = "", // 1413
        var hospitalType: String = "", // 0
        var bedId: Int = 0, // 1474
        var locName: String = "", // 心血管内科病区
        var roomCode: String = "", // 1413-214
        var adm: AdmBean = AdmBean(),
        var locId: Int = 0, // 20
        var roomId: Int = 0, // 87
        var bedConfig: BedConfigBean = BedConfigBean()
    ) {
        @Keep
        data class AdmBean(
            var admConfig: AdmConfigBean = AdmConfigBean(),
            var patId: Int = 0, // 119
            var foreignPhone: String = "", // 13535068230
            var admNo: String = "", // 9269219
            var bedDate: String = "", // 2019-01-20
            var total: String = "", // 7927.94
            var foreignName: String = "", // 李仁香
            var balance: String = "", // -2927.94
            var nurse: NurseBean = NurseBean(),
            var admReason: String = "", // 自费
            var patNo: String = "", // 0007090188
            var doctorNurseList: List<DoctorNurseBean> = listOf(),
            var events: List<EventBean> = listOf(),
            var admId: Int = 0, // 61
            var bedDay: Int = 0, // 1179
            var sex: String = "", // 男
            var bedId: Int = 0, // 1474
            var birth: String = "", // 2019-01-20
            var doctor: DoctorBean = DoctorBean(),
            var obsItem: List<ObsItemBean> = listOf(),
            var inNo: String = "", // 00405267
            var name: String = "", // 李仁香小仔
            var deposit: String = "", // 5000.00
            var matron: MatronBean = MatronBean(),
            var diaDesc: String = "", // 早产低出生体重儿
            var allergyNo: Int = 0 // 0
        ) {
            @Keep
            data class AdmConfigBean(
                var exam: Boolean = false, // false
                var inspect: Boolean = false, // false
                var diagnosis: Boolean = false // false
            )

            @Keep
            data class NurseBean(
                var code: String = "", // 684
                var intro: String = "", // 多年的三甲医院护理经验，熟练掌握心肺复苏、气管插管、气管切开、洗胃、各类穿刺及清创止血骨折固定等急救护理工作。能熟练的操作各种医疗器械，对外科患者的术前、术后护理工作流程掌握熟练。
                var name: String = "", // 洪*琼
                var photo: String = "", // http://10.0.0.23/upload/photo/1560847907819.jpg
                var id: Int = 0, // 384
                var title: String = "", // 护士
                var type: String = "" // N
            )

            @Keep
            data class DoctorNurseBean(
                var name: String = "", // 简晓芳
                var photo: String = "", // http://10.0.0.23/upload/photo/1560847907770.jpg
                var id: Int = 0, // 3164
                var title: String = "", // 护士长
                var type: String = "", // N
                var code: String = "", // 910
                var intro: String = ""
            )

            @Keep
            data class EventBean(
                var eventId: Int = 0, // 1617
                var code: String = "", // 1413-603
                var iconDisplay: String = "", // 0
                var detailDisplay: Int = 0, // 0
                var icon: String = "",
                var id: Int = 0, // 1022
                var source: String = "", // C
                var sort: Int = 0, // 0
                var bedSort: Int = 0, // 1
                var intervention: String = "",
                var desc: String = "", // 法国
                var color: String = "" // #fca610
            )

            @Keep
            data class DoctorBean(
                var code: String = "", // 910
                var intro: String = "",
                var name: String = "", // 林菱
                var photo: String = "", // http://10.0.0.23/static/image/photo-men.jpg
                var id: Int = 0, // 1595
                var title: String = "", // 医生
                var type: String = "" // D
            )

            @Keep
            data class ObsItemBean(
                var id: Int = 0, // 12
                var name: String = "", // 舒张压
                var uom: String = "", // mmHg
                var chooseIcon: String = "", // http://10.0.0.23/upload/observation/1602485984248.png
                var showIcon: String = "", // http://10.0.0.23/upload/observation/1602485997739.png
                var symbol: String = "", // emptyCircle
                var symbolSize: Int = 0, // 10
                var value: String = "", // 1

            )

            @Keep
            data class MatronBean(
                var name: String = "", // 简晓芳
                var photo: String = "", // http://10.0.0.23/upload/photo/1560847907770.jpg
                var id: Int = 0, // 3164
                var title: String = "", // 护士长
                var type: String = "" // N
            )
        }

        @Keep
        data class BedConfigBean(
            var urgent: Boolean = false // false
        )
    }
}