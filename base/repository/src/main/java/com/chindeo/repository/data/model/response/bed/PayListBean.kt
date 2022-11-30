package com.chindeo.repository.data.model.response.bed

/**
 * Created by xiemaohui on 2022/5/11
 */
data class PayListBean (
    val payDetailAmount : String? = null,// "22000",
    val prtPaymodeDesc : String? = null,// "软POS1",
    val printDate : String? = null,// "2019-01-22",
    val prtNO: String? = null, // "98933496",
    val printTime : String? = null,// "15:52:16"
//    val pATPatientNo:String?=null, //"0007090188",
//    val department:String?=null,// "新生儿科",
//    val pAADMAdmNo:String?=null, //"9269219",
//    val pAADMInPatNo:String?=null, //"00405267",
//    val wardDesc:String?=null //"新生儿病区"
)