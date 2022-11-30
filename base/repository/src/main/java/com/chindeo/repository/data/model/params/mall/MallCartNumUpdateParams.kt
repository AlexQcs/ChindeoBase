package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField

/**
 * 修改商品数量请求参数
 * Created by xiemaohui on 2022/5/13
 */
class MallCartNumUpdateParams(
        @JSONField(name = "cartNum")
        val cartNum: Int
)