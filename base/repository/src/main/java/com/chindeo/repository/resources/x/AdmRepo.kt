package com.chindeo.repository.resources.x

import com.chindeo.repository.util.http.RetrofitManager

class AdmRepo() : BaseRepository() {


//    private val mService by lazy { RetrofitManager.instance.create(AdmApi::class.java) }



    companion object{
        @Volatile
        private var netWork: AdmRepo? = null

        fun getInstance() = netWork ?: synchronized(this) {
            netWork ?: AdmRepo().also { netWork = it }
        }
    }
}