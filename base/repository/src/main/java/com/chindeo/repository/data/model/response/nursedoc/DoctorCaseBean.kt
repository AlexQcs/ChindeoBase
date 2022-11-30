package com.chindeo.repository.data.model.response.nursedoc

/**
 * Created by zqs on 2022/5/31
 */
 class DoctorCaseBean {
    var charpterName:String?=null
    var files:MutableList<String>?=null
    var charpterId:String?=null
    var isSelect:String?="N"

    override fun toString(): String {
        return "DoctorCaseBean(charpterName=$charpterName, files=$files, charpterId=$charpterId, isSelect=$isSelect)"
    }
}