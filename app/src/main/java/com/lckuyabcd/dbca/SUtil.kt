package com.lckuyabcd.dbca

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object SUtil {
    @JvmStatic
    fun getScreenSize(context: Context): DisplayMetrics {
        val metrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        display.getMetrics(metrics)
        return metrics
    }

    @JvmStatic
    fun getDeviceDensity(context: Context): Float {
        val metrics = DisplayMetrics()
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        return metrics.density
    }
}