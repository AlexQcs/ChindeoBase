package com.chindeo.repository.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chindeo.repository.data.model.common.PageLimit
import com.chindeo.repository.data.model.params.bed.BedQueryJianYanParams
import com.chindeo.repository.data.model.response.arcitem.PatientJianYanBean
import com.chindeo.repository.data.model.response.base.ResultState
import com.chindeo.repository.resources.x.ArcItemRepo

class QueryJianYanPagingSource(val repo: ArcItemRepo, val config: BedQueryJianYanParams) : PagingSource<Int, PatientJianYanBean>() {


    override fun getRefreshKey(state: PagingState<Int, PatientJianYanBean>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PatientJianYanBean> {
        var currentPage = params.key ?: 1
        val response = repo.getQueryJianYan(config, PageLimit(currentPage))
        return if (response.isSuccess()) {
            val data = response.data
            LoadResult.Page(
                    data = data.list!!,
                    prevKey = null,
                    nextKey = if (data.hasNextPage) data.nextPage else null
            )
        } else {
            LoadResult.Page(
                    data = mutableListOf(),
                    prevKey = null,
                    nextKey = null
            )
        }

    }
}