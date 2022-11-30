package com.chindeo.repository.data.api;

import com.chindeo.repository.contants.ApiConstants;
import com.chindeo.repository.data.model.nurses.ListDeviceBean;
import com.chindeo.repository.data.model.params.RequestContent;
import com.chindeo.repository.data.model.response.HttpResult;
import com.chindeo.repository.data.model.response.adm.AdmZhenliaoListBean;
import com.chindeo.repository.data.model.response.bed.BedDetailBean;
import com.chindeo.repository.data.model.response.bed.BedDeviceConfigBean;
import com.chindeo.repository.data.model.response.bed.BedQueryPriceListBean;
import com.chindeo.repository.data.model.response.bed.BedQueryQindanSumBean;
import com.chindeo.repository.data.model.response.bed.BedRoleAuthorityBean;
import com.chindeo.repository.data.model.response.bed.BedUnReadNoticeBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeDeleteBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeDetailBean;
import com.chindeo.repository.data.model.response.bed.BedVolumeSetBean;
import com.chindeo.repository.data.model.response.bed.EventEnrollmentBean;
import com.chindeo.repository.data.model.response.bed.HostTrusteeShipBean;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface BedApi {


    /**
     * 根据设备获取病区床位信息
     *
     * @param params
     * @return
     */
    @GET(ApiConstants.API_NURSES_BED_LIST)
    Flowable<HttpResult<ListDeviceBean>> getListDevice(@QueryMap RequestContent params);

    /**
     * 床位信息更新
     *
     * @param params
     * @return
     */
    @POST(ApiConstants.API_NURSES_BED_UPDATE)
    Flowable<HttpResult<Boolean>> bedUpdate(@Body RequestContent params);

    /**
     * 紧急呼叫消息已读
     *
     * @return
     */
    @POST(ApiConstants.API_REMIND_READ)
    Flowable<HttpResult<String>> remindRead(@Body RequestContent params);


    @GET(ApiConstants.API_BED_DEVICE_CONFIG)
    Flowable<HttpResult<BedDeviceConfigBean>> deviceConfig(@QueryMap RequestContent params);


    /**
     * 获取病床详情
     *
     * @param bedId
     * @return
     */
    @GET(ApiConstants.API_BED_DETAIL)
    Flowable<HttpResult<BedDetailBean>> getDetail(@Query("bedId") String bedId);

    /**
     * 获取未读消息
     */
    @GET(ApiConstants.API_BED_MESSAGE_UN_READ)
    Flowable<HttpResult<List<BedUnReadNoticeBean>>> messageUnRead(@Query("admId") String admId);


    /**
     * 获取菜单列表
     */
    @GET(ApiConstants.API_BED_MENU)
    Flowable<HttpResult<BedRoleAuthorityBean>> bedMenu();


    /**
     * 获取查询清单列表
     */
    @GET(ApiConstants.API_QUERY_QIN_DAN)
    Flowable<HttpResult<BedQueryQindanSumBean>> getQueryQindan(@Query("admNo") String admNo);

    /**
     * 获取查询清单详情
     */
    @GET(ApiConstants.API_QUERY_QIN_DAN_DETAIL)
    Flowable<HttpResult<String>> getQueryQindanDetail(@Query("admNo") String admNo, @Query("date")String date);

    /**
     * 获取物价分类
     * @return
     */
    @GET(ApiConstants.API_QUERY_PRICE_CATEGORY)
    Flowable<HttpResult<List<String>>> getQueryPriceCategory();

    /**
     * 获取物价分类 - 详情
     * @return
     */
    @GET(ApiConstants.API_QUERY_PRICE_CATEGORY_DETAIL)
    Flowable<HttpResult<BedQueryPriceListBean>> getQueryPriceCategoryDetail(@QueryMap RequestContent params);

    /**
     * 获取诊疗 - 详情
     * @return
     */
    @GET(ApiConstants.ADM_ZHENLIAO_PLAN)
    Flowable<HttpResult<List<AdmZhenliaoListBean>>> getZhenliaoPlan(@QueryMap RequestContent params);

    /**
     * 设置消息提醒已读
     */
    @POST(ApiConstants.API_TIXING_SET_READ)
    Flowable<HttpResult<String>>  setMessageRead(@Query("msgId")int msgId);
}