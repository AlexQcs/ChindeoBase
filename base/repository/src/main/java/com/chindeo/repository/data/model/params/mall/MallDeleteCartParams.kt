package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField

/**
 * 批量删除购物车商品
 * Created by xiemaohui on 2022/5/13
 */
class MallDeleteCartParams(
        @JSONField(name = "ids")
        val ids: List<Int>,
)