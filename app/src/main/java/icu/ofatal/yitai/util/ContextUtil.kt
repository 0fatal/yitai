package icu.ofatal.yitai.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper

/**
 * 基于Context寻找Activity
 */
fun Context.findActivity(): Activity = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> error("Failed to find activity: ${this.javaClass.name}")
}