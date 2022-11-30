package com.chindeo.repository.data.model.response.nursedoc

/**
 * 医生护士登录
 */
data class NurseDocLoginBean(val loginId: String = "",
                             val name: String = "",
                             val locName: String = "",
                             val photo: String = "",
                             val position: String = "",
                             val type: String = "", //N
                             val userId: String = "", //11
                             val token: String = "")