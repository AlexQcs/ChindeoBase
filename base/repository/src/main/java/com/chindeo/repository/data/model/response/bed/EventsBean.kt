package com.chindeo.repository.data.model.response.bed

import com.chad.library.adapter.base.entity.JSectionEntity
import java.io.Serializable

/**护理事件
 * Created by zqs on 2022/5/19
 */
class EventsBean: JSectionEntity, Serializable {
    var color: String? = null
    var id: Int = 0
    var sort: Int? = null //首页排序
    var type: String? = null
    var desc: String? = null
    var eventId: Int = 0
    var iconDisplay: String? = null
    var detailDisplay: String? = null
    var icon: String? = null
    var source: String? = null
    var bedSort: String? = null  //普通排序
    var intervention: String? = null
    var childList:MutableList<EventsBean>?=null
    var isHear: Boolean? = false
    var title:String?=null
    var select:Boolean=false
    var rightTitle:String?=null
    var isSelect:String?="Y" //N 不选中 Y选中
    var extId:String?=null
    var locId:Int=0
    var name:String?=null


    override val isHeader: Boolean
        get() = isHear!!
    constructor() : super()
    constructor(title: String?,desc: String?,rightTitle:String?, isHear: Boolean?) : super() {
        this.desc = desc
        this.isHear = isHear
        this.title = title
        this.rightTitle=rightTitle
    }

    override fun toString(): String {
        return "EventsBean(color=$color, id=$id, sort=$sort, type=$type, desc=$desc, eventId=$eventId, iconDisplay=$iconDisplay, detailDisplay=$detailDisplay, icon=$icon, source=$source, bedSort=$bedSort, intervention=$intervention, childList=$childList, isHear=$isHear, title=$title, select=$select, rightTitle=$rightTitle, isSelect=$isSelect, extId=$extId, locId=$locId, name=$name)"
    }

}