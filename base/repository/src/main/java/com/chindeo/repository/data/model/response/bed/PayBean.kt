package com.chindeo.repository.data.model.response.bed

/**
 * Created by zqs on 2022/5/10
 */
data class PayBean (
    var balance:Double?= 0.00, //余额
   // var billDate:String?=null, //日期
    var billSum:Double?=0.0,  //总和
  //  var dateSums:Int?=0,   //日期总和
    var deposit:Double?=0.0, //预交金
    var sumFee:Double?=0.0   //总费用
)