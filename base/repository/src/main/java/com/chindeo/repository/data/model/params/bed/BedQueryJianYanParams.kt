package com.chindeo.repository.data.model.params.bed

import androidx.fragment.app.Fragment

class BedQueryJianYanParams{
        var admId: String =""
        var orderFrom: String=""
        var orderTo: String=""
        var statusCode: String?=null // 结果状态代码:[A:医嘱审核（待发报告）;E:Entered;V:审核报告;BK:预约;SC:登记;IP:开始检查;IM:有图像;RP:已写报告;CM:检查完成;
//    CA:取消检查;R:已接收;RF:已拒收;RE:已报告;CBK:取消预约;CRE:取消报告;TF:取消报告;OPP:冲突]


      constructor()
      constructor(admId: String,orderFrom: String,orderTo: String) {
          this.admId = admId
          this.orderFrom = orderFrom
          this.orderTo=orderTo
      }

      constructor(admId: String,orderFrom: String,orderTo: String,statusCode: String?) {
          this.admId = admId
          this.orderFrom = orderFrom
          this.orderTo=orderTo
          this.statusCode=statusCode
      }
}