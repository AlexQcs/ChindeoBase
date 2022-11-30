package com.chindeo.repository.data.model.nurses

import androidx.annotation.Keep
import com.apkfuns.logutils.LogUtils
import com.lazylibs.util.TimeUtils
import java.text.SimpleDateFormat
import java.util.*

@Keep
data class TimingTaskListBean(val attachTab: String,
                              val task: TimingTaskBean
) {

    @Keep
    data class TimingTaskBean(
            val hour: Int, val minute: Int, val code: String
    ) {
        var time: Int = 1 // 次数
        var areaCodeList: MutableList<Int>? = null
        var content: String? = null

        //媒体特有两个属性
        var url: String? = null
        var mediaName: String? = null

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as TimingTaskBean

            if (hour != other.hour) return false
            if (minute != other.minute) return false

            return true
        }

        override fun hashCode(): Int {
            var result = hour
            result = 31 * result + minute
            return result
        }

        fun getTimeFormat(): String {
            return getHourFormat() + ":" + getMinuteFormat()
        }

        private fun getHourFormat(): String {
            if (hour.toString().length == 1) {
                return "0$hour"
            }
            return hour.toString()
        }

        private fun getMinuteFormat(): String {
            if (minute.toString().length == 1) {
                return "0$minute"
            }
            return minute.toString()
        }


//        fun getTaskTimeStamp(): Long {
//            val calendar = Calendar.getInstance()
//            calendar.time = getDate()
//            calendar.set(Calendar.HOUR_OF_DAY, hour)
//            calendar.set(Calendar.MINUTE, minute)
//            calendar.set(Calendar.SECOND, 0)
//
//            if (System.currentTimeMillis() > calendar.timeInMillis) { // 已经是过去时间
//                calendar.add(Calendar.DATE, 1)
//            }
////            LogUtils.d("定时任务开始时间 ${TimeUtils.millis2String(calendar.timeInMillis)} 内容-> $content")
//            return calendar.timeInMillis
//        }


//        fun getDate(): Date {
//            when (code) {
//                TimingRepeatType.WORKDAY.code -> {
//                    val c = Calendar.getInstance()
//                    val d = Date()
//                    c.time = d
//                    c.set(Calendar.HOUR_OF_DAY, 0)
//                    c.set(Calendar.MINUTE, 0)
//                    c.set(Calendar.SECOND, 0)
//                    c.set(Calendar.MILLISECOND, 0)
//                    val eStr = TimeUtils.getNowString(SimpleDateFormat("EEEE", Locale.getDefault()))
//                    if (eStr == "星期六") {
//                        c.add(Calendar.DATE, 2)
////                        LogUtils.d("定时任务在周一到周五，今天是星期六，推算下周一的时间为${TimeUtils.millis2String(c.timeInMillis)}")
//                    } else if (eStr == "星期日") {
//                        c.add(Calendar.DATE, 1)
////                        LogUtils.d("定时任务在周一到周五，今天是星期日，推算下周一的时间为${TimeUtils.millis2String(c.timeInMillis)}")
//                    }
//                    return Date(c.timeInMillis)
//                }
//                TimingRepeatType.WEEKEND.code -> {
//                    val c = Calendar.getInstance(Locale.CHINA)
//                    c.timeInMillis = System.currentTimeMillis()
//                    Date().apply {
//                        val eStr = TimeUtils.getNowString(SimpleDateFormat("EEEE", Locale.getDefault()))
//                        if (eStr != "星期六" && eStr != "星期日") {
//                            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY)
//                        }
//                    }
//                    c.set(Calendar.HOUR_OF_DAY, 0)
//                    c.set(Calendar.MINUTE, 0)
//                    c.set(Calendar.SECOND, 0)
//                    return Date(c.timeInMillis)
//                }
//                else -> {
//                    if (TimingRepeatType.isEEEE(code)) {
//                        val c = Calendar.getInstance(Locale.CHINA)
//                        c.timeInMillis = System.currentTimeMillis()
//                        c.set(Calendar.DAY_OF_WEEK, code) // 设置星期
//                        c.set(Calendar.HOUR_OF_DAY, hour)
//                        c.set(Calendar.MINUTE, minute)
//                        c.set(Calendar.SECOND, 0)
//                        val todayC = Calendar.getInstance(Locale.CHINA)
//                        todayC.timeInMillis = System.currentTimeMillis()
////                        LogUtils.d("今天 -> ${TimeUtils.millis2String(todayC.timeInMillis)} , 定时日期 -> ${TimeUtils.millis2String(c.timeInMillis)}")
////                        LogUtils.d("相差 -> ${todayC.get(Calendar.DAY_OF_WEEK) - c.get(Calendar.DAY_OF_WEEK)}")
//                        if (todayC.timeInMillis > c.timeInMillis) {
//                            c.add(Calendar.DAY_OF_YEAR, 7)
////                            LogUtils.d("处理后日期 -> ${TimeUtils.millis2String(c.timeInMillis)}")
//                        }
//                        return Date(c.timeInMillis)
//                    }
//                }
//            }
//            return Date()
//        }

        override fun toString(): String {
            return "TimingTaskBean(hour=$hour, minute=$minute, code=$code, time=$time, areaCodeList=$areaCodeList, content=$content, url=$url, mediaName=$mediaName)"
        }


    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TimingTaskListBean

        if (attachTab != other.attachTab) return false

        if (task != other.task) return false

        return true
    }

    override fun hashCode(): Int {
        return attachTab.hashCode()
    }


}
