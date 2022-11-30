package com.chindeo.repository.data.model.params.nurses

/**
 *  旧版商城登录
 * Created by xiemaohui on 2022/8/26
 */
data class VisitLoginParams(
    val username:String?,
    val password:String?,
    val app_secret:String,
    val app_id:String,
    val auth_type:String,
) {

}