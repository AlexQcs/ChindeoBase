package com.chindeo.repository.data.model.response.bed

import android.os.Parcelable
import com.alibaba.fastjson.annotation.JSONField
import kotlinx.parcelize.Parcelize
import java.io.Serializable

/**
 * Created by xiemaohui on 2022/6/27
 */

@Parcelize
data class BedCareEvaluationBean (
    @JSONField(name = "admId")
    var admId: Int?=null,
    @JSONField(name = "locId")
    var locId: Int?=null,
    @JSONField(name = "norms") //评价指标
    var norms: List<Norm>?=null,
    @JSONField(name = "photo")
    var photo: String?=null,
    @JSONField(name = "submit")
    var submit: String?=null,
    @JSONField(name = "type")
    var type: String?=null,
    @JSONField(name = "userId")
    var userId: Int?=null,
    @JSONField(name = "userName")
    var userName: String?=null,
    @JSONField(name = "title")
    var title: String? = null, //职称
    @JSONField(name = "intro")
    var intro: String? = null, //介绍
) : Parcelable {

    @Parcelize
    data class Norm(
        @JSONField(name = "avg")  //平均
        var avg: Double?=null,
        @JSONField(name = "content")  //内容
        var content: String?=null,
        @JSONField(name = "normId")  //指标id
        var normId: Int?=null,
        @JSONField(name = "normName") //指标名
        var normName: String?=null,
        @JSONField(name = "score") //分数
        var score: Int?=0,
        @JSONField(name = "type")  //1星，2笑脸，3文本
        var type: String?=null
    ) : Parcelable{
        override fun toString(): String {
            return "\nNorm(avg=$avg, content=$content, normId=$normId, normName=$normName, score=$score, type=$type)"
        }
    }

    override fun toString(): String {
        return "\nBedCareEvaluationBean(admId=$admId, locId=$locId, norms=$norms, photo=$photo, submit=$submit, type=$type, userId=$userId, userName=$userName, title=$title, intro=$intro)"
    }
}

