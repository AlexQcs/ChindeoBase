package com.chindeo.repository.data.model.nurses

import java.util.*

enum class TimingRepeatType(val code: String, val desc: String) {

    // 保留功能
    WORKDAY("0", "周一至周五"),
    WEEKEND("1", "周末"),

//    Monday(Calendar.MONDAY, "星期一"),
//    Tuesday(Calendar.TUESDAY, "星期二"),
//    Wednesday(Calendar.WEDNESDAY, "星期三"),
//    Thursday(Calendar.THURSDAY, "星期四"),
//    Friday(Calendar.FRIDAY, "星期五"),
//    Saturday(Calendar.SATURDAY, "星期六"),
//    Sunday(Calendar.SUNDAY, "星期日"),
    EVERYDAY("2", "每天"),
    NOT_REPEAT("3", "不重复"),
    ;

    //
//    WORKING("0"),
//    WEEKEND("1"),
//    EVERYDAY("2"),
//    UN_REPEAT("3");

    companion object {

        fun getByCode(code: String): TimingRepeatType? {
            for (value in values()) {
                if (value.code == code) {
                    return value
                }
            }
            return null
        }

        fun getList(): MutableList<TimingRepeatType> {
            val list = values().toMutableList()
            list.remove(WORKDAY)
            list.remove(WEEKEND)
            return list
        }

//        fun isEEEE(code: Int): Boolean {
//             getByCode(code)?.let {
//                return it == Monday || it == Tuesday || it == Wednesday || it == Thursday || it == Friday || it == Saturday || it == Sunday
//            }
//            return false
//        }
    }
}