package com.chindeo.repository.data.api.kt

import com.chindeo.repository.contants.ApiConstants
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.response.HttpResult
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface EducationApi {


    @GET(ApiConstants.EDUCATION_LIST)
    suspend fun getList(@QueryMap params : RequestContent): HttpResult<String>
}