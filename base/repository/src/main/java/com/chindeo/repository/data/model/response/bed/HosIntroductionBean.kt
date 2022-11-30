package com.chindeo.repository.data.model.response.bed

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class HosIntroductionBean(@SerializedName("data")
                               val data: MutableList<DataItem>??,
                               @SerializedName("menu")
                               val menu: String? = ""): Parcelable
