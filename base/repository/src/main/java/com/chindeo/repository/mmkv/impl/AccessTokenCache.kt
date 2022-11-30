package com.chindeo.repository.mmkv.impl

import android.text.TextUtils
import com.chindeo.repository.mmkv.ICache

object AccessTokenCache {


    private val APP_ACCESS_TOKEN = "app_access_token" //共享app之间的token

    private var cache: ICache? = null

    private fun cache(): ICache {
        if (cache == null) {
            cache = ICache.accessToken()
        }
        return cache!!
    }

    fun setAccessToken(accessToken: String?) {
        if (TextUtils.isEmpty(accessToken)) {
            cache().remove(APP_ACCESS_TOKEN)
        }else{
            cache().put(APP_ACCESS_TOKEN, accessToken!!)
        }
    }

    fun getAccessToken(): String {
        return cache().getString(APP_ACCESS_TOKEN)
    }


}