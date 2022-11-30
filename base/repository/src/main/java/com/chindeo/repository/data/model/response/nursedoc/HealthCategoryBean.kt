package com.chindeo.repository.data.model.response.nursedoc

import com.chindeo.repository.data.model.response.bed.BedDetailBean

/**
 * Created by xiemaohui on 2022/7/21
 */
 data class HealthCategoryBean(
    var times: MutableList<String>? = null,
    var items: MutableList<BedDetailBean.AdmBean.ObsItemBean>? =null
 )