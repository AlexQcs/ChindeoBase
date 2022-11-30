package com.chindeo.repository.data.api

import com.chindeo.repository.contants.ApiConstants
import com.chindeo.repository.data.model.response.HttpResult
import com.chindeo.repository.data.model.response.base.BasePagingResult
import com.chindeo.repository.data.model.response.bed.EventsBean
import com.chindeo.repository.data.model.response.doctor.DiagnosticRecordBean
import com.chindeo.repository.data.model.response.doctor.DoctorAdviceBean
import com.chindeo.repository.data.model.response.nursedoc.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

/**医生护士 ApiServices
 * Created by zqs on 2022/5/16
 */
interface NurseDocApiService {

    @POST(ApiConstants.APP_CHECK_BED_USER)
    suspend fun getPasLogin(@Body map: MutableMap<String,Any>): HttpResult<NurseDocLoginBean>

    @POST(ApiConstants.SERVICE_FACE_IDENTIFY)
    suspend fun getFaceLogin(@Body map: MutableMap<String,Any>): HttpResult<NurseDocLoginBean>

    @GET(ApiConstants.APP_BED_NURSE)
    suspend fun getTubeBedSettingNurse(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<TubeBedSettingBean>>

    @GET(ApiConstants.BED_DOCTOR)
    suspend fun getTubeBedSettingDoctor(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<TubeBedSettingBean>>

    @GET(ApiConstants.ROTA_LOC_NURSES)
    suspend fun getLocNurse(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<TubeBedSettingBean>>

    @GET(ApiConstants.ROTA_LOC_DOCTORS)
    suspend fun getLocDoctor(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<TubeBedSettingBean>>

    @POST(ApiConstants.APP_BED_USER_SET)
    suspend fun getSetDoctorAndNurse(@Body map: MutableMap<String,Any>): HttpResult<MutableList<TubeBedSettingBean>>

    @GET(ApiConstants.APP_BED_NURSE_EVENT)
    suspend fun getNurseEventList(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<EventsBean>>

    @POST(ApiConstants.APP_ADM_NURSE_EVENT_EXT_ADD)
    suspend fun getNurseEventAdd(@Body map: MutableMap<String,Any>): HttpResult<EventsBean>

    @POST(ApiConstants.APP_ADM_NURSE_EVENT_EXT_DEL)
    suspend fun getNurseEventDelete(@Body map: MutableMap<String,Any>): HttpResult<EventsBean>

    @POST(ApiConstants.APP_ADM_NURSE_EVENT_EXT_SORT)
    suspend fun getNurseEventSort(@Body map: MutableMap<String,String>): HttpResult<EventsBean>

    @GET(ApiConstants.APP_ADM_NURSE_EVENT_EXT_TAG)
    suspend fun getNurseEventExtTagList(): HttpResult<MutableList<EventsBean>>

    @GET(ApiConstants.APP_ORDER_DOC_LIST)
    suspend fun getOrderDocList(): HttpResult<MutableList<OrderExecutionBean>>

    @GET(ApiConstants.APP_ORDER_DOC_INFO)
    suspend fun getOrderDocInfoList(@QueryMap map: MutableMap<String,Any>): HttpResult<OrderExecutionInfoBean>

    @GET(ApiConstants.APP_DIAGNOSE_ADM)
    suspend fun getDiagnosticRecordList(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<DiagnosticRecordBean>?>

    @GET(ApiConstants.APP_ARC_ITEM_PATIENT_ORDER)
    suspend fun getDoctorAdviceList(@QueryMap map: MutableMap<String,Any>): HttpResult<BasePagingResult<DoctorAdviceBean>>

    @GET(ApiConstants.APP_ARC_ITEM_PATIENT_SPECIMEN)
    suspend fun getDoctorCheckResultList(@QueryMap map: MutableMap<String,Any>): HttpResult<BasePagingResult<CheckResultBean>>

    @GET(ApiConstants.APP_ARC_ITEM_PATIENT_EXAM)
    suspend fun getDoctorInspectionResultsList(@QueryMap map: MutableMap<String,Any>): HttpResult<BasePagingResult<InspectionResultsBean>>

    @GET(ApiConstants.APP_ARC_ITEM_RIS_REPORT)
    suspend fun getDoctorInspectionResultsUrl(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<InspectionResultsBean?>>

    @GET(ApiConstants.ARC_ITEM_PATIENT_EMR)
    suspend fun getDoctorCaseMenuList(@QueryMap map: MutableMap<String,Any>): HttpResult<MutableList<DoctorCaseBean>>

    @GET(ApiConstants.ARC_ITEM_PATIENT_SPECIMEN_RESULT)
    suspend fun getDoctorCheckResultDetail(@QueryMap map: MutableMap<String,Any>): HttpResult<CheckResultDetailBean>

    @POST(ApiConstants.APP_GET_CLOCK_IN_LIST)
    suspend fun getClockInList(@Body map: MutableMap<String,Any>): HttpResult<MutableList<ClockVoListBean>?>

    @POST(ApiConstants.APP_GET_UPDATE_CLOCK)
    suspend fun setClockIn(@Body map: MutableMap<String,Any>): HttpResult<String>

    @POST(ApiConstants.APP_GET_CHECK_CLOCK_IN_START)
    suspend fun checkClockInStart(@Body map: MutableMap<String, Any>): HttpResult<Boolean>

    @GET(ApiConstants.APP_GET_NURSE_STATUS)
    suspend fun getNurseStatus(): HttpResult<NursingNurseStatusBean?>

    @POST(ApiConstants.APP_POST_CHECK_ROOM_STATUS)
    suspend fun checkRoomStatus(@Query("roomId")roomId:String): HttpResult<NursingCheckStatusBean>

    @POST(ApiConstants.APP_POST_ROOM_STATUS)
    suspend fun upDateRoomStatus(@Query("roomId")roomId:String,@Query("type")type:String): HttpResult<String>

    @GET(ApiConstants.APP_GET_REINFORCEMENTS)
    suspend fun getReinforcements(@Query("locCode")locCode:String): HttpResult<NursingReinforcementsBean>

    @POST(ApiConstants.APP_POST_DISCOVER_REMIND)
    suspend fun addDiscoverRemind(@Body map: MutableMap<String, Any>): HttpResult<String?>

    @GET(ApiConstants.APP_GET_DISCOVER_REMIND_LIST)
    suspend fun getDiscoverRemindList(@Query("admId")admId:String): HttpResult<MutableList<SettingRemindBean>>

    @GET(ApiConstants.APP_DELETE_DISCOVER_REMIND)
    suspend fun deleteDiscoverRemind(@Query("id")id:Int): HttpResult<String>

//    @POST(ApiConstants.APP_POST_AUDIO_UPLOAD)
//    suspend fun postDoctorAudio(@Body paramsBody: RequestBody) :HttpResult<String>

    @Multipart
    @POST(ApiConstants.APP_POST_AUDIO_UPLOAD)
    suspend fun postDoctorAudio2(@Part file: MultipartBody.Part,@PartMap map:Map<String, @JvmSuppressWildcards RequestBody>) :HttpResult<String?>

    @GET(ApiConstants.APP_GET_TI_ZHENG_CATEGORY)
    suspend fun getHealthList(@Query("locId")locId:Int) :HttpResult<HealthCategoryBean>

    @POST(ApiConstants.POST_TI_ZHENG_ITEMS)
    suspend fun addHealthUpload(@Body body:RequestBody) :HttpResult<String?>

    @POST(ApiConstants.API_TIXING_SET_READ)
    suspend fun setMessageRead(@Query("msgId")msgId :Int): HttpResult<String>
}