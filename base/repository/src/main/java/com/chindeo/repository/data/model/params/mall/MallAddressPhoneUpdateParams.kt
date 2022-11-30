package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField

class MallAddressPhoneUpdateParams(
        @JSONField(name = "phone")
        val phone: String
)