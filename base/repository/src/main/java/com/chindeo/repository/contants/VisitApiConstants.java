package com.chindeo.repository.contants;

/**
 * 探视预约
 * Created by xiemaohui on 2022/8/26
 */
public interface VisitApiConstants {

//    String API_POST_VISIT_LOGIN ="api/v1/get_access_token";//获取token
//
//    String API_POST_ViSIT_REFRESH ="api/v1/refresh_access_token"; //刷新token


    String API_GET_VISIT_TIMES="index/device.remote/times"; //远程探视预约时间

    String API_GET_VISIT_PASS_TIME = "index/device.remote/passTime"; //通过远程探视预约

    String API_GET_VISIT_REJECT_TIME = "index/device.remote/rejectTime"; //驳回探视预约

}
