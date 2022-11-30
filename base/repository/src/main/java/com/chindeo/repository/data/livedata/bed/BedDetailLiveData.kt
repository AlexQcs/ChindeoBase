package com.chindeo.repository.data.livedata.bed

import androidx.lifecycle.MutableLiveData
import com.chindeo.repository.data.model.response.bed.BedDetailBean

enum class BedDetailLiveData {

    INSTANCE;


    private var liveData: MutableLiveData<BedDetailBean>? = null

    open fun getLiveData(): MutableLiveData<BedDetailBean> {
        if (liveData == null) {
            liveData = MutableLiveData<BedDetailBean>()
        }
        return liveData!!
    }

    companion object {

        fun post(bedDetail: BedDetailBean?) {
            INSTANCE.getLiveData().postValue(bedDetail)

        }

        fun get(): BedDetailBean? {
            return INSTANCE.getLiveData().value
        }

        fun getAdmNo(): String? {
            get()?.let {
                return it.adm?.admNo
            }
            return "0"
        }

        fun getLocCode(): String? {
            get()?.let {
                return it.locCode
            }
            return null
        }

        fun getAdmId(): String? {
            get()?.let {
                return it.adm?.admId
            }
            return null
        }

        fun getAdm(): BedDetailBean.AdmBean? {
            get()?.let {
                return it.adm
            }
            return null
        }
    }
}
