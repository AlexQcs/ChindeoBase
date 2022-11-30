package com.chindeo.repository.data.model.response.bed


import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import com.alibaba.fastjson.annotation.JSONField
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class BedTiXingMessageBean(
        var read: Int? = 0, // 0未读
        var createTime: String? = null, // 2022-03-09 08:10:05
        var msgNo: String? = null, // null
        var sendType: String? = null, // sendType	发送人类型，0:系统，1:患者端，2:护士病区端，3:房间端
        var id: Int? = null, // 58038
        var type: Int? = null, // 0：文本；1：图片：2：视频，3：音频，4：文档
        var title: String? = "", // 2
        var annex: AnnexBean? = null,
        var content: String? = null, // 经鼻口吸痰法
        val mandatory : String? = null //1为强制阅读，
) : Parcelable {
    fun isMandatory(): Boolean {
        return (!TextUtils.isEmpty(this.mandatory)
                && TextUtils.equals(this.mandatory, "1"))
    }


    @Keep
    data class AnnexBean(
        var cover: String? = null, // http://10.0.0.23/upload/education/1616991141925.jpg
        @JSONField(name = "file")
        var file: String? = null, // http://10.0.0.23/upload/education/1616991141894.mp4
        var itemObjType: String? = null, // http://10.0.0.231
        var id: Int? = null, // 96
        var title: String? = null // 经鼻口吸痰法
    ) : Parcelable {

        constructor(file: String?):this()

        constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readString()) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(cover)
            parcel.writeString(file)
            parcel.writeString(itemObjType)
            parcel.writeValue(id)
            parcel.writeString(title)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<AnnexBean> {
            override fun createFromParcel(parcel: Parcel): AnnexBean {
                return AnnexBean(parcel)
            }

            override fun newArray(size: Int): Array<AnnexBean?> {
                return arrayOfNulls(size)
            }
        }
    }
}