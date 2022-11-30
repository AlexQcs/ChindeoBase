package com.chindeo.repository.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS
import android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
import androidx.appcompat.widget.AppCompatImageView

class BatteryOptimizationUtil {
    companion object {
        /**
         * 忽略电池优化
         */
        fun ignoreBatteryOptimization(context: Context) {
            val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            var hasIgnored = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                hasIgnored = powerManager.isIgnoringBatteryOptimizations(context.packageName)
                //  判断当前APP是否有加入电池优化的白名单，如果没有，弹出加入电池优化的白名单的设置对话框。
                if (!hasIgnored) {
                    //未加入电池优化的白名单 则弹出系统弹窗供用户选择(这个弹窗也是一个页面)
                    val intent = Intent(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                    intent.data = Uri.parse("package:" + context.packageName)
                    context.startActivity(intent)
                } else {
                    //已加入电池优化的白名单 则进入系统电池优化页面
                    val powerUsageIntent = Intent(ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                    val resolveInfo = context.packageManager.resolveActivity(powerUsageIntent, 0)
                    //判断系统是否有这个页面
                    if (resolveInfo != null) {
                        context.startActivity(powerUsageIntent)
                    }
                }
            }
        }


        fun Context.queryBatteryOptimizeStatus(): Boolean {
            val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager?
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                powerManager?.isIgnoringBatteryOptimizations(packageName) ?: false
            } else {
                true
            }
        }

    }

}