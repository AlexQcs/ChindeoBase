package com.chindeo.repository.data.model.response.bed

/**
 * 满意评价bean
 * Created by xiemaohui on 2022/6/22
 */
data class BedEvaluationBean(
//    var createTime: String, //创建时间
//    var direction: String, //问卷说明
//    var duration: Int, //	规定时长 单位为分钟
//    var eduIds: List<Any>, //绑定宣教id
    var endTime: String, //问卷结束时间
    var href: String, //访问连接
    var id: Int, //
 //   var locId: Int, // 病区ID
    var menu: String, //问卷菜单
//    var publishState: String, //发布状态，0未发布，1已发布
//    var publishTime: String, //发布时间
    var startTime: String, //问卷开始时间
//    var thanks: String, //问卷感谢语
//    var title: String, //问卷名称
//    var type: String, //问卷类型,1:问卷调查,2:压疮护理:,3:管道脱落,4跌倒坠床风险:,5:疼痛,6:自理能力,7:患者评估单,8:满意度调查,9:培训评价,10:考试题目,11:题库,12:宣教评价
//    var updateTime: String, //更新时间
//    var userId: Int //发布人
)