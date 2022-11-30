//package com.chindeo.repository.data.livedata.nurses
//
//import androidx.lifecycle.MutableLiveData
//import com.chindeo.repository.data.model.nurses.TimingTaskListBean
//import com.chindeo.repository.mmkv.impl.NursesCache
//
//enum class NursesBroadcastTaskLiveData() {
//
//    INSTANCE;
//
//    private var liveData: MutableLiveData<List<TimingTaskListBean>>? = null
//
//
//    fun getLiveData(): MutableLiveData<List<TimingTaskListBean>> {
//        if (liveData == null) {
//            liveData = object : MutableLiveData<List<TimingTaskListBean>>() {
//                override fun getValue(): List<TimingTaskListBean>? {
//                    return super.getValue() ?: NursesCache.getTimingTaskList()
//                }
//            }
//        }
//        return liveData!!
//    }
//
//    companion object {
//
//        fun get(): List<TimingTaskListBean>? {
//            return INSTANCE.getLiveData().value
//        }
//
//        fun post(list: MutableList<TimingTaskListBean>) {
//            INSTANCE.getLiveData().postValue(list)
//        }
//    }
//
//
//}