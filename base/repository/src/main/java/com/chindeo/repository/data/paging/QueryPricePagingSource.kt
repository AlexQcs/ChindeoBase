package com.chindeo.repository.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.chindeo.repository.data.model.common.PageLimit
import com.chindeo.repository.data.model.params.bed.BedQueryPriceDetailParams
import com.chindeo.repository.data.model.response.base.ResultState
import com.chindeo.repository.data.model.response.bed.BedQueryPriceListBean
import com.chindeo.repository.resources.x.BedRepo

class QueryPricePagingSource(val bedRepo: BedRepo, val config: BedQueryPriceDetailParams) : PagingSource<Int, BedQueryPriceListBean>() {

    override fun getRefreshKey(state: PagingState<Int, BedQueryPriceListBean>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BedQueryPriceListBean> {
        var currentPage = params.key ?: 1
        val response = bedRepo.getQueryPriceDetailList(config, PageLimit(currentPage, params.loadSize))
        val data = response.data
        return if (response.isSuccess()) {
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