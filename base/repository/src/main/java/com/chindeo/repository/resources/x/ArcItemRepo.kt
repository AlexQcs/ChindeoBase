package com.chindeo.repository.resources.x

import com.chindeo.repository.data.api.kt.ArcItemApi
import com.chindeo.repository.data.model.common.PageLimit
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.params.bed.BedQueryJianYanParams
import com.chindeo.repository.data.model.response.arcitem.PatientJianChaBean
import com.chindeo.repository.data.model.response.arcitem.PatientJianYanBean
import com.chindeo.repository.data.model.response.base.BasePagingResult
import com.chindeo.repository.data.model.response.base.ResultState
import com.chindeo.repository.util.http.RetrofitManager

class ArcItemRepo private constructor(): BaseRepository() {

    private val api: ArcItemApi by lazy { RetrofitManager.instance.create(ArcItemApi::class.java) }
    suspend fun getQueryJianYan(params: BedQueryJianYanParams, pageLimit: PageLimit) = api.getJianYan(RequestContent.createRepositoryParams(params, pageLimit))
    suspend fun getQueryJianCha(params: BedQueryJianYanParams, pageLimit: PageLimit) = api.getJianCha(RequestContent.createRepositoryParams(params, pageLimit))

    companion object {
        @Volatile
        private var network: ArcItemRepo? = null
        fun getInstance() = network ?: synchronized(this) {
            network ?: ArcItemRepo().also { network = it }
        }
    }

}