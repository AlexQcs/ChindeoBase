package com.chindeo.repository.data.model.response.nursedoc

import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by zqs on 2022/5/19
 */
class OrderExecutionBean {
    // {"id":1,"hospitalId":1,"code":"JCD","name":"检查单"}
    var id: Int = 0
    var hospitalId: Int = 0
    var code: String? = null
    var name: String? = null
    var url: String? = null
    var isSelect: String? = "N"
    var cols: MutableList<OrderExecutionBean>? = null
    var rows: JSONArray? = null
    var newList:MutableList<OrderExecutionBean>?=null


    //获取 内容
    open fun getContentValue(code:String): String? {
        if (rows != null) {
            for (i in 0 until rows!!.length()){
                var obj: JSONObject = rows!!.get(i) as JSONObject
                return obj.getString(code)
            }
        }
        return null
    }

    open fun getId(): String? {
        if (rows != null) {
            for (i in 0 until rows!!.length()){
                var obj: JSONObject = rows!!.get(i) as JSONObject
                return obj.getString("id")
            }
        }
        return null
    }




}