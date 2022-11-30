package com.chindeo.repository.data.model.response.nursedoc

import androidx.annotation.Keep
import org.json.JSONArray

/**
 * Created by xiemaohui on 2022/5/19
 */
data class OrderExecutionInfoBean (
    var cols: MutableList<ColsBean>? = null, //医嘱字段配置列表，顶部标题
//    var rows: JSONArray?=null,
    var rows: MutableList<MutableMap<String,String>>?=null  //行数据
)
{
    data class ColsBean(
        var code: String? = null,
        var name: String?=null,//名称
        var id: Int? = null,
        var url:String?=null
//        var col: String? = null, //表列名
//        var source:String?=null//来源，0医嘱表，1执行表，2检验表，4检查表，5手术表
    )
}