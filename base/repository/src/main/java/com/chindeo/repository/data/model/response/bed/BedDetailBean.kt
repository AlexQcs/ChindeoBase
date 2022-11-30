package com.chindeo.repository.data.model.response.bed


import androidx.annotation.Keep
import com.chindeo.repository.data.livedata.bed.BedDetailLiveData

@Keep
data class BedDetailBean(
    var adm: AdmBean?,
    var bedCode: String = "", // 38
    var bedId: Int = 0, // 1488
    var hospitalType: String = "", // 0
    var locCode: String = "", // 1413
    var locId: Int = 0, // 20
    var locName: String = "", // 心血管内科病区
    var roomCode: String = "", // 1413-217
    var roomId:String?=null, //病房id
    var bedConfig:UrgentBean? //紧急呼叫 屏蔽发送
) {

    fun isEmptyBed(): Boolean {
        return adm == null
    }


    fun getAdmNo(): String {
        adm?.let {
            return it.admNo
        }
        return "0"
    }

    @Keep
    data class UrgentBean(
        val urgent:Boolean =false
    )

    @Keep
    data class AdmBean(
        var admId: String = "", // 316
        var admNo: String = "0", // 9263899
        var admReason: String ?= "", // 广东异地医保
        var allergyNo: Int = 0, // 0
        var balance: String = "", // -12948.97
        var bedDate: String = "", // 2019-01-19
        var bedDay: String = "0", // 1011
        var bedId: Int = 0, // 1488
        var birth: String = "", // 1957-05-03
        var deposit: String = "", // 0.00
        var diaDesc: String = "", // 高血压
        var events: List<EventsBean> = listOf(),
        var foreignName: String = "", // 陈信
        var foreignPhone: String? = "", // 18923866142
        var idNumber: String? = "", // 420102195705031421
        var inNo: String = "", // 00245058
        var name: String = "", // 陈丽娜
        var obsItem: List<ObsItemBean> = listOf(),
//        var obsItemEvent: ObsItemEventBean?=null, //护理事件登记字典
        var admState:String?="",//护理事件登记名
        var patId: Int = 0, // 49
        var patNo: String? = "", // 0000055249
        var sex: String = "", // 女
        var age: String? = "", // 女
        var total: String = "", // 12948.97
        var doctorNurseList: List<JobItemBean>? = null,
        var doctor:JobItemBean?=null,
        var nurse:JobItemBean?=null,
        val admConfig:AdmConfigBean?=AdmConfigBean()
    ) {
        @Keep
        data class EventBean(
            val code: String? = "", // cs007
            val color: String? = "", // #FF9EFFA4
            val desc: String? = "", // yyuihjnbnm
            val detailDisplay: Int = 0, // 0
            val eventId: Int = 0, // 0
            val icon: String? = "",
            val iconDisplay: String? = "", // 1
            val id: Int = 0, // 994
            val sort: Int = 0, // 0
            val source: String? = "" // C
        )

        @Keep
        data class ObsItemEventBean( //护理事件登记字典
            val code:String?=null,//护理事件登记代码
            val id:String?=null,
            val name:String?=null
        )

        @Keep
        data class AdmConfigBean(
            val exam: Boolean? = false,//false= 不屏蔽 检查结果 true=屏蔽
            val inspect: Boolean? = false,//false= 不屏蔽 检验结果 true=屏蔽
            val diagnosis: Boolean? = false
        )

        @Keep
        data class ObsItemBean(
            var chooseIcon: String? = "", // http://10.0.0.23/upload/observation/1602485984248.png
            var showIcon: String? = "", // http://10.0.0.23/upload/observation/1602485997739.png
            var id: Int = 0, // 12
            var name: String? =null, // 舒张压
            var symbol: String? = "", // emptyCircle
            var symbolSize: Int = 0, // 10
            var uom: String? = "--", // mmHg
            var value: String? =null,// 70
            var max: Double?=null,//最大值
            var min: Double?=null,//最小值
            var capacityType: String?=null //容量类型，1：入量，2：出量
        ){
            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as ObsItemBean

                if (id != other.id) return false

                return true
            }

            override fun hashCode(): Int {
                return id?.hashCode() ?: 0
            }

//        fun getStandardTv(): String {
//
//        }
        }


        //   {
        //     "code": "2462",
        //     "intro": "多年的三甲医院护理经验，熟练掌握心肺复苏、气管插管、气管切开、洗胃、各类穿刺及清创止血骨折固定等急救护理工作。能熟练的操作各种医疗器械，对外科患者的术前、术后护理工作流程掌握熟练。",
        //     "name": "罗雨虹",
        //     "photo": "http:\/\/10.0.0.23\/upload\/photo\/1560847907802.jpg",
        //     "id": 2500,
        //     "title": "护士",
        //     "type": "N"
        //   }
        @Keep
        data class JobItemBean(
            val code: String? = "",
            val intro: String? = "",
            val name: String? = "",
            val photo: String? = "",
            val id: Int? =null,
            val title: String? = "", //职称
            val type: String? = "", // N护士 D医生
        )
    }


}
