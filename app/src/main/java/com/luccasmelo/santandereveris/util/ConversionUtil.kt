package com.luccasmelo.santandereveris.util

import android.content.res.Resources
import android.util.TypedValue

fun String.toDp(resources:Resources):Int{
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.replace("[dp]".toRegex(),"").toFloat(),
        resources.displayMetrics).toInt()
}