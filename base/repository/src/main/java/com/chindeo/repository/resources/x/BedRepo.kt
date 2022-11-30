package com.chindeo.repository.resources.x

import com.chindeo.repository.data.api.kt.BedApi
import com.chindeo.repository.data.model.common.PageLimit
import com.chindeo.repository.data.model.params.RequestContent
import com.chindeo.repository.data.model.params.bed.*
import com.chindeo.repository.data.model.response.bed.BedCareEvaluationBean
import com.chindeo.repository.util.http.RetrofitManager

class BedRepo private constructor() : BaseRepository() {

    private val bedApi: BedApi by lazy { RetrofitManager.instance.create(BedApi::class.java) }

    suspend fun getQindanSum(admNo: String) = bedApi.getQueryQindanSum(admNo)

    //病例列表
    suspend fun getCaseMenuList(map: MutableMap<String,Any>) = bedApi.getCaseMenuList(map)

    // 清单详情
    suspend fun getQindanDetail(admNo: String, date: String) = bedApi.getQueryQindanDetail(admNo, date)

    // 物价分类标题
    suspend fun getQueryPriceCategoryList() = bedApi.getPriceCategoryList()

    //检查结果详情
    suspend fun getDoctorInspectionResultsUrl(map: MutableMap<String,Any>) = bedApi.getDoctorInspectionResultsUrl(map)

    //检验结果查询
    suspend fun getCheckResultDetail(map: MutableMap<String,Any>)=bedApi.getCheckResultDetail(map)

    // 物价详情列表
    suspend fun getQueryPriceDetailList(params: BedQueryPriceDetailParams, pageLimit: PageLimit) = bedApi.getPriceDetailList(RequestContent.createRepositoryParams(params, pageLimit))

    // 生命体征
    suspend fun getObHealth(admId: String) = bedApi.getObHealthList(admId)

    // 生命体征设置
    suspend fun setObHealth(admId: String, observationIds: List<Int>) = bedApi.setObHealthList(RequestContent.create(BedObHealthParams(admId, observationIds)))

    // 修改患者生命体征
    suspend fun writeAdmItem(map: MutableMap<String, Any>)=bedApi.writeAdmItem(map)

    // 智能提醒分类
    suspend fun getTiXingCategory() = bedApi.getTiXingCategory()

    // 智能提醒列表
    suspend fun getTiXingMessageList(params: BedTiXingMsgListParams, pageLimit: PageLimit) = bedApi.getTiXingMessageList(RequestContent.createRepositoryParams(params, pageLimit))

    // 健康宣教 业务类
    suspend fun getEducationList(params: EducationListParams) = bedApi.getEducationList(RequestContent.create(params))

    //快速缴费详情
    suspend fun getBillDetailSummary(admNo: String) = bedApi.getBillDetailSummary(admNo)

    //查询所有医院介绍
    suspend fun getHospitalIntroduce(map: MutableMap<String,Any>) = bedApi.getHospitalIntroduce(map)

    //消费记录
    suspend fun getBillDetailDeposit(admNo: String)= bedApi.getBillDetailDeposit(admNo)

    //满意评价
    suspend fun getSurveyList(admId: Int)=bedApi.getSurveyList(admId)

    //获取当天医护人员评价 患者id,病区id
    suspend fun getCareEvaluation(admId: Int,locId: Int)=bedApi.getCareEvaluation(admId,locId)

    //提交当天医护人员评价
    suspend fun submitCareEvaluation(bean :List<BedCareEvaluationBean>)=bedApi.submitCareEvaluation(bean)

    //获取智能家居列表
    suspend fun getSmartHomeList(admId: Int)=bedApi.getSmartHomeList(admId)

    //控制智能家居开关 operate 0开  1关
    suspend fun getSmartDeviceOperate(driveId: Int,operate: Int)=bedApi.getSmartDeviceOperate(driveId,operate)

    //设置消息已读
    suspend fun setMessageRead(msgId: Int)=bedApi.setMessageRead(msgId)

    //知识百科类型， 标签
    suspend fun getEncyclopediaType(type:String)=bedApi.getEncyclopediaType(type)

    //知识百科列表
    suspend fun getEncyclopediaList(map: MutableMap<String,Any>)=bedApi.getEncyclopediaList(map)

    //获取诊疗
    suspend fun getZhenliaoPlan(params: BedZhenliaoPlanParams)  = bedApi.getZhenliaoPlan(RequestContent.create(params))

    //获取宣教评价url
    suspend fun getEducationEvalUrl(map: MutableMap<String, Any>)=bedApi.getEducationEvalUrl(map)

    //验证患者身份证号后四位数字
    suspend fun checkAdmIdentity(map:MutableMap<String, Any>)=bedApi.checkAdmIdentity(map)

    companion object {
        @Volatile
        private var netWork: BedRepo? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: BedRepo().also { netWork = it }
        }
    }

}