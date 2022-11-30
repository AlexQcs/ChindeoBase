package com.chindeo.repository.data.model.response.nursedoc

import android.text.TextUtils
import com.alibaba.fastjson.annotation.JSONField
import com.lazylibs.weight.slide.ChineseToPinyinHelper

data class TubeBedSettingBean(
    @JSONField(name = "code")
    val decode: String = "",
    val observation: String = "",
    val room: String = "",
    val reserved: String = "",
    val id: Int = 0,
    val urgent: String = "",
    val locId: Int = 0,
    var isSelect: String = "N",
    val name:String?="",
    val title:String?="",
    val photo:String?="",
    var firstLetter: String? = null,
    var pinyin:String?=null
){
    fun setPinyinAndLetter() {
        if (!TextUtils.isEmpty(name)) {
            val convert: String = ChineseToPinyinHelper.getInstance().getPinyin(name).uppercase()
            this.pinyin=convert
            val substring: String = convert.substring(0, 1)
            if (substring.matches(Regex("[A-Z]"))) {
                this.firstLetter=substring
            } else {
                this.firstLetter="#"
            }
        }
    }
}