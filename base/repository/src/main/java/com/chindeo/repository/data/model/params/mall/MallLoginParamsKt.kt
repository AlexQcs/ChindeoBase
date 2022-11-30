package com.chindeo.repository.data.model.params.mall

import com.alibaba.fastjson.annotation.JSONField


class MallLoginParamsKt(
        @JSONField(name = "uuid")
        var uuid: String = "",
        @JSONField(name = "name")
        var name: String = "",
        @JSONField(name = "phone")
        var phone: String? = "",
        @JSONField(name = "room_num")
        var roomNum: String? = "",
        @JSONField(name = "sex")
        var sex: Int? = 1,// 1男2女
        @JSONField(name = "locName")
        var locName: String = "",
        @JSONField(name = "bedNum")
        var bedNum: String? = "",
        @JSONField(name = "hospitalNo")
        var hospitalNo: String = "", // 住院号
        @JSONField(name = "disease")
        var disease: String? = ""
) {}