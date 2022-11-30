package com.chindeo.repository.data.model.response.base

import com.alibaba.fastjson.annotation.JSONField

data class BasePagingResult<T>(

        var total: Int,
        var pageNum: Int,
        var pageSize: Int,
        var prePage: Int,
        var nextPage: Int,
        var isFirstPage: Boolean,
        var isLastPage: Boolean,
        var hasNextPage: Boolean,
        @JSONField(name = "list")
        var list: List<T>? = null

)