package com.chindeo.repository.data.model.response.bed


import androidx.annotation.Keep
import com.alibaba.fastjson.annotation.JSONField

@Keep
data class BedSickSignsListBean(
    var all :MutableList<BedDetailBean.AdmBean.ObsItemBean>,
    var select:MutableList<BedDetailBean.AdmBean.ObsItemBean>
) {

    @Keep
    data class BedSickSignsItemBean(
        var chooseIcon: String? = null, // http://10.0.0.23/upload/observation/1602484943065.png
        var id: String? = null, // 42
        var name: String? = null, // 身高
        var showIcon: String? = null, // http://10.0.0.23/upload/observation/1602484943068.png
        var symbol: String? = null, // emptyCircle图表图标
        var symbolSize: Int? = null, // 10 图表图标大小
        var uom: String? = null, // cm 单位
        var value: String? = null, // 180 值
        var max: Double?=null,//最大值
        var min: Double?=null,//最小值
        var capacityType: String?=null //容量类型，1：入量，2：出量
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as BedSickSignsItemBean

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

}