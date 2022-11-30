package com.chindeo.repository.data.api.kt

import com.chindeo.repository.contants.ApiConstants
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.response.HttpResult
import com.chindeo.repository.data.model.response.adm.AdmZhenliaoListBean
import com.chindeo.repository.data.model.response.base.BasePagingResult
import com.chindeo.repository.data.model.response.bed.*
import com.chindeo.repository.data.model.response.device.SmartDeviceBean
import com.chindeo.repository.data.model.response.mall.PageData
import com.chindeo.repository.data.model.response.nursedoc.CheckResultDetailBean
import com.chindeo.repository.data.model.response.nursedoc.DoctorCaseBean
import com.chindeo.repository.data.model.response.nursedoc.InspectionResultsBean
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.*

interface BedApi {

    @GET(ApiConstants.API_QUERY_QIN_DAN)
    suspend fun getQueryQindanSum(@Query("admNo") admNo: String): HttpResult<BedQueryQindanSumBean>

    @GET(ApiConstants.APP_ARC_ITEM_RIS_REPORT)
    suspend fun getDoctorInspectionResultsUrl(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<InspectionResultsBean?>>
    /**
     * 获取查询清单详情
     */
    @GET(ApiConstants.API_QUERY_QIN_DAN_DETAIL)
    suspend fun getQueryQindanDetail(@Query("admNo") admNo: String, @Query("date") date: String): HttpResult<List<BedQueryQindanChartItemBean>>

    @GET(ApiConstants.API_QUERY_PRICE_CATEGORY)
    suspend fun getPriceCategoryList(): HttpResult<List<String>>

    @GET(ApiConstants.ARC_ITEM_PATIENT_EMR)
    suspend fun getCaseMenuList(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<DoctorCaseBean>>

    @GET(ApiConstants.ARC_ITEM_PATIENT_SPECIMEN_RESULT)
    suspend fun getCheckResultDetail(@QueryMap map: MutableMap<String,Any>): HttpResult<CheckResultDetailBean>

    @GET(ApiConstants.API_QUERY_PRICE_CATEGORY_DETAIL)
    suspend fun getPriceDetailList(@QueryMap params: RequestContent): HttpResult<BasePagingResult<BedQueryPriceListBean>>

    @GET(ApiConstants.API_OBSERVATION_HEALTH)
    suspend fun getObHealthList(@Query("admId") admId: String): HttpResult<BedSickSignsListBean>

    @POST(ApiConstants.API_OBSERVATION_HEALTH_SET)
    suspend fun setObHealthList(@Body params: RequestContent): HttpResult<String>

    @POST(ApiConstants.API_POST_WRITE_AdmItem)
    suspend fun writeAdmItem(@Body map: MutableMap<String, Any>):HttpResult<String?>

    @GET(ApiConstants.API_TIXING_CATEGORY)
    suspend fun getTiXingCategory(): HttpResult<List<BedTiXingCategoryBean>>

    @GET(ApiConstants.API_TIXING_UNREAD_LIST)
    suspend fun getTiXingMessageList(@QueryMap params: RequestContent): HttpResult<PageData<BedTiXingMessageBean>>

    @POST(ApiConstants.API_TIXING_SET_READ)
    suspend fun setMessageRead(@Query("msgId")msgId :Int): HttpResult<String>

    @GET(ApiConstants.EDUCATION_LIST)
    suspend fun getEducationList(@QueryMap params: RequestContent): HttpResult<List<EducationBean>>

    @GET(ApiConstants.APP_BILL_DETAIL_SUMMARY)
    suspend fun getBillDetailSummary(@Query("admNo") admNo: String): HttpResult<PayBean>

    @GET(ApiConstants.APP_BILL_DETAIL_DEPOSIT)
    suspend fun getBillDetailDeposit(@Query("admNo") admNo: String): HttpResult<List<PayListBean>>

    @GET(ApiConstants.APP_HOSPITALINTRODUCE_OBTAININTRODUCES)
    suspend fun getHospitalIntroduce(@QueryMap map: MutableMap<String, Any>): HttpResult<MutableList<HosIntroductionBean>>

    @GET(ApiConstants.API_SURVEY_LIST)
    suspend fun getSurveyList(@Query("admId") admNo: Int): HttpResult<MutableList<BedEvaluationBean>>

    @GET(ApiConstants.GET_YI_HU_EVALUATE_ITEMS)
    suspend fun getCareEvaluation(@Query("admId") admNo: Int,@Query("locId") locId: Int): HttpResult<MutableList<BedCareEvaluationBean>>

    @POST(ApiConstants.POST_EVALUATE_DATA)
    suspend fun submitCareEvaluation(@Body beans :List<BedCareEvaluationBean>) : HttpResult<String?>

    @GET(ApiConstants.GET_SMART_DEVICE_LIST)
    suspend fun getSmartHomeList(@Query("admId") admNo: Int): HttpResult<MutableList<SmartDeviceBean>>

    @GET(ApiConstants.GET_SMART_DEVICE_OPERATE)
    suspend fun getSmartDeviceOperate(@Query("id") id: Int,@Query("operate") operate: Int): HttpResult<Boolean>

    @GET(ApiConstants.GET_ZSBK_TYPE)
    suspend fun getEncyclopediaType(@Query("type")type:String):HttpResult<MutableList<EncyclopediaBean>>

    @GET(ApiConstants.GET_ZSBK_LIST)
    suspend fun getEncyclopediaList(@QueryMap map: MutableMap<String, Any>):HttpResult<PageData<EncyclopediaBean>>

    @GET(ApiConstants.ADM_ZHENLIAO_PLAN)
    suspend fun getZhenliaoPlan(@QueryMap params: RequestContent): HttpResult<MutableList<AdmZhenliaoListBean>>

    @GET(ApiConstants.getEducationEvalUrl)
    suspend fun getEducationEvalUrl(@QueryMap map: MutableMap<String, Any>): HttpResult<String?>

    @POST(ApiConstants.POST_IDENTITY)
    suspend fun checkAdmIdentity(@Body map: MutableMap<String, Any>):HttpResult<String?>
}