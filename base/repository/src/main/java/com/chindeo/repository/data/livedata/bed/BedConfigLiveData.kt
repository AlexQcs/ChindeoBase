package com.chindeo.repository.data.livedata.bed

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.alibaba.fastjson.JSON
import com.apkfuns.logutils.LogUtils
import com.chindeo.repository.RepositoryComponent
import com.chindeo.repository.data.model.response.bed.BedDeviceConfigBean
import com.chindeo.repository.mmkv.impl.SettingHostCache
import com.lazylibs.util.ToastUtils

enum class BedConfigLiveData {

    INSTANCE;


    private var liveData: MutableLiveData<BedDeviceConfigBean?>? = null

    open fun getLiveData(): MutableLiveData<BedDeviceConfigBean?> {
        if (liveData == null) {
            liveData = MutableLiveData<BedDeviceConfigBean?>()
        }
        return liveData!!
    }

    companion object {

        fun init(config: BedDeviceConfigBean?) {
            SettingHostCache.cacheMallHostUrl(config?.carer?.domain)
            INSTANCE.getLiveData().postValue(config)

        }

        fun config(): BedDeviceConfigBean? {
            return INSTANCE.getLiveData().value
        }

        fun hostLinphone():String?{
            if (config()==null){
              LogUtils.d("BedConfigLiveData HostLinphone 无病床配置信息。")
                return null
            }

            var userName = StringBuilder()

            if (!config()!!.hostGroupUsers.isNullOrEmpty()) {
                val hos: List<BedDeviceConfigBean.HostUserBean> = config()?.hostGroupUsers!!
                LogUtils.e( "TAG hostGroupUsers:  --- " + JSON.toJSONString(hos))
                for (i in hos.indices) {
                    if (!TextUtils.isEmpty(hos[i].userName)){
                        userName.append(hos[i].userName)
                        if (i != hos.size - 1) {
                            userName.append(",")
                        }
                    }
                }
            } else {
                val hostUser: BedDeviceConfigBean.HostUserBean? = config()!!.hostUser
                userName.append(hostUser?.userName)
            }

            if (TextUtils.isEmpty(userName)) {
                ToastUtils.showLong(RepositoryComponent.INSTANCE, "BedConfigLiveData HostLinphone 服务端无Linphone配置。")
                LogUtils.d("BedConfigLiveData HostLinphone 服务端无Linphone配置。")
                return null
            }

            return userName.toString()
        }
    }
}
