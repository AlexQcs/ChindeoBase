package com.chindeo.repository.data.model.response.arcitem


import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep

@Keep
data class PatientImageCaseBean(
    val charpterName: String? = null,
    val files: List<String>? = null,
    val charpterId: Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.readValue(Int::class.java.classLoader) as? Int) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(charpterName)
        parcel.writeStringList(files)
        parcel.writeValue(charpterId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PatientImageCaseBean> {
        override fun createFromParcel(parcel: Parcel): PatientImageCaseBean {
            return PatientImageCaseBean(parcel)
        }

        override fun newArray(size: Int): Array<PatientImageCaseBean?> {
            return arrayOfNulls(size)
        }
    }
}