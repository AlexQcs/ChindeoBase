package com.chindeo.repository.data.model.response.doctor

import com.chad.library.adapter.base.entity.MultiItemEntity

data class DoctorAdviceBean(val execTime: String?=null,
                            val subNo: String?=null,
                            val execUser: String?=null,
                            val orderSum: String ?=null,
                            val groupIcon: String?=null,
                            val execDate: String?=null,
                            val orderTime: String?=null,
                            val billFlag: String?=null,
                            val name: String?=null,
                            val id: Int = 0,
                            val user: String?=null,
                            val orderDate: String?=null,
                            val stopUser:String?=null,
                            val discoUser:String?=null,
                            override var itemType: Int
): MultiItemEntity