package com.chindeo.repository.data.model.response.bed


import com.alibaba.fastjson.annotation.JSONField
import androidx.annotation.Keep

@Keep
data class BedUnReadNoticeBean(
    val messages: List<MessageBean?>? = listOf(),
    val row: Int? = 0, // 18
    val sortTime: String? = "", // 2019-07-17 10:17:41
    val title: String? = "", // 测试推送
    val unReadNum: Int? = 0 // 1
) {
    @Keep
    data class MessageBean(
        val annex: AnnexBean? = AnnexBean(),
        val content: String? = "", // 为您护航  从“心”开始
        val createTime: String? = "", // 2019-07-17 10:17:41
        val id: Int? = 0, // 357
        val type: String? = "", // 4
    ) {

        var parentTitle: String? = ""

        @Keep
        data class AnnexBean(
            val cover: String? = "", // http://10.0.0.23/upload/education/1562065735576.png
            @JSONField(name = "file")
            val fileUrl: String? = "", // http://10.0.0.23/upload/education/1562065733101.pdf
            val id: Int? = 0, // 47
            val title: String? = "" // 为您护航  从“心”开始
        )
    }
}