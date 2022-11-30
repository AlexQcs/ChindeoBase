package com.chindeo.repository.data.livedata.base

import androidx.lifecycle.MutableLiveData
import com.chindeo.repository.data.model.response.HttpResult

class StateLiveData<T> : MutableLiveData<HttpResult<T>>()