package com.chindeo.repository.resources.x

import com.chindeo.repository.data.api.NurseDocApiService
import com.chindeo.repository.util.http.RetrofitManager
import com.lazylibs.util.SpUtils
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by zqs on 2022/5/16
 */
class NurseDocRepository private constructor(): BaseRepository() {
    companion object {
        @Volatile
        private var netWork: NurseDocRepository? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: NurseDocRepository().also { netWork = it }
        }

    }
    private val mApiService: NurseDocApiService by lazy { RetrofitManager.instance.create(NurseDocApiService::class.java) }

    suspend fun getPasLogin(map: MutableMap<String,Any>) = mApiService.getPasLogin(map)

    suspend fun getFaceLogin(map: MutableMap<String,Any>) = mApiService.getFaceLogin(map)

    suspend fun getTubeBedSettingDoctor(map: MutableMap<String,Any>) = mApiService.getTubeBedSettingDoctor(map)

    suspend fun getTubeBedSettingNurse(map: MutableMap<String,Any>) = mApiService.getTubeBedSettingNurse(map)

    suspend fun getLocNurse(map: MutableMap<String,Any>) = mApiService.getLocNurse(map)

    suspend fun getLocDoctor(map: MutableMap<String,Any>) = mApiService.getLocDoctor(map)

    suspend fun getSetDoctorAndNurse(map: MutableMap<String,Any>) = mApiService.getSetDoctorAndNurse(map)

    suspend fun getNurseEventList(map: MutableMap<String,Any>) = mApiService.getNurseEventList(map)

    suspend fun getNurseEventAdd(map: MutableMap<String,Any>) = mApiService.getNurseEventAdd(map)

    suspend fun getNurseEventDelete(map: MutableMap<String,Any>) = mApiService.getNurseEventDelete(map)

    suspend fun getNurseEventSort(map: MutableMap<String,String>) = mApiService.getNurseEventSort(map)

    suspend fun getNurseEventExtTagList() = mApiService.getNurseEventExtTagList()

    suspend fun getOrderDocList() = mApiService.getOrderDocList()

    suspend fun getOrderDocInfoList(map: MutableMap<String,Any>) = mApiService.getOrderDocInfoList(map)

    suspend fun getDiagnosticRecordList(map: MutableMap<String,Any>) = mApiService.getDiagnosticRecordList(map)

    suspend fun getDoctorAdviceList(map: MutableMap<String,Any>) = mApiService.getDoctorAdviceList(map)

    suspend fun getDoctorCheckResultList(map: MutableMap<String,Any>) = mApiService.getDoctorCheckResultList(map)

    suspend fun getDoctorInspectionResultsList(map: MutableMap<String,Any>) = mApiService.getDoctorInspectionResultsList(map)

    suspend fun getDoctorInspectionResultsUrl(map: MutableMap<String,Any>) = mApiService.getDoctorInspectionResultsUrl(map)

    suspend fun getDoctorCaseMenuList(map: MutableMap<String,Any>) = mApiService.getDoctorCaseMenuList(map)

    suspend fun getDoctorCheckResultDetail(map: MutableMap<String,Any>)=mApiService.getDoctorCheckResultDetail(map)

    suspend fun getClockInList(map : MutableMap<String,Any>)=mApiService.getClockInList(map)

    suspend fun setClockIn(map : MutableMap<String,Any>)=mApiService.setClockIn(map)

    suspend fun checkClockInStart(map : MutableMap<String,Any>)=mApiService.checkClockInStart(map)

    suspend fun getNurseStatus()=mApiService.getNurseStatus()

    suspend fun getReinforcements(locCode:String)=mApiService.getReinforcements((locCode))

    suspend fun checkRoomStatus(roomId:String)=mApiService.checkRoomStatus(roomId)

    suspend fun upDateRoomStatus(roomId:String,type:String)=mApiService.upDateRoomStatus(roomId,type)

    suspend fun addDiscoverRemind(map : MutableMap<String,Any>)=mApiService.addDiscoverRemind(map)

    suspend fun getDiscoverRemindList(admId:String)=mApiService.getDiscoverRemindList(admId)

    suspend fun deleteDiscoverRemind(id:Int)=mApiService.deleteDiscoverRemind(id)

//    suspend fun postDoctorAudio(paramsBody: RequestBody)=mApiService.postDoctorAudio(paramsBody)

    suspend fun postDoctorAudio2(file: MultipartBody.Part,map:Map<String, @JvmSuppressWildcards RequestBody>)=mApiService.postDoctorAudio2(file,map)

    suspend fun getHealthList(locId:Int)=mApiService.getHealthList(locId)

    suspend fun addHealthUpload(body:RequestBody)=mApiService.addHealthUpload(body)

    suspend fun setMessageRead(msgId: Int)=mApiService.setMessageRead(msgId)
}