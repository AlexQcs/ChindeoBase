package com.chindeo.repository.data.api.kt

import com.chindeo.repository.contants.ApiConstants
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.response.HttpResult
import com.chindeo.repository.data.model.response.arcitem.PatientImageCaseBean
import com.chindeo.repository.data.model.response.arcitem.PatientInfo
import com.chindeo.repository.data.model.response.arcitem.PatientJianChaBean
import com.chindeo.repository.data.model.response.arcitem.PatientJianYanBean
import com.chindeo.repository.data.model.response.base.BasePagingResult
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ArcItemApi {


    @GET(ApiConstants.ARC_ITEM_PATIENT)
    suspend fun getPatientInfo(@Query("admId") admId: String): HttpResult<PatientInfo>

    @GET(ApiConstants.ARC_ITEM_PATIENT_CASE)
    suspend fun getPatientImageCase(@Query("admId") admId: String): HttpResult<List<PatientImageCaseBean>>

    @GET(ApiConstants.ARC_ITEM_PATIENT_CASE)
    suspend fun getPatientHtmlCase(@Query("admNo") admNo: String): HttpResult<String>

    @GET(ApiConstants.ARC_ITEM_PATIENT_JIANYAN)
    suspend fun getJianYan(@QueryMap params: RequestContent): HttpResult<BasePagingResult<PatientJianYanBean>>

    @GET(ApiConstants.ARC_ITEM_PATIENT_JIANCHA)
    suspend fun getJianCha(@QueryMap params: RequestContent): HttpResult<BasePagingResult<PatientJianChaBean>>


}