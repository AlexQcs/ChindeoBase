package com.chindeo.repository.data.api

import com.chindeo.repository.contants.ApiConstants
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.response.HttpResult
import io.reactivex.Flowable
import retrofit2.http.*

interface BusinessCommonApi {

    @POST(ApiConstants.API_SHIELD_PATIENT)
    fun shieldPatient(@Body params: RequestContent): Flowable<HttpResult<String>>


}