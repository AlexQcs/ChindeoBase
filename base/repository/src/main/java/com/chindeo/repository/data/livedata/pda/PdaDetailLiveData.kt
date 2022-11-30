package com.chindeo.repository.data.livedata.pda

import androidx.lifecycle.MutableLiveData
import com.chindeo.repository.data.model.nurses.ListDeviceBean
import com.chindeo.repository.data.model.pda.PdaDetailBean
import io.reactivex.observers.DisposableSingleObserver

enum class PdaDetailLiveData {
    INSTANCE;


    private var liveData: MutableLiveData<PdaDetailBean>? = null
    private val dataDis: DisposableSingleObserver<ListDeviceBean>? = null

    open fun getLiveData():MutableLiveData<PdaDetailBean>{
        if(liveData==null){
            liveData=MutableLiveData<PdaDetailBean>()

        }

        return liveData!!
    }

    open fun init(){
        if (dataDis!=null){
            return
        }



    }

    companion object{
        fun post(pdaDetail: PdaDetailBean){
            INSTANCE.getLiveData().postValue(pdaDetail)
        }

        fun get():PdaDetailBean?{
            return INSTANCE.getLiveData().value
        }



    }

}