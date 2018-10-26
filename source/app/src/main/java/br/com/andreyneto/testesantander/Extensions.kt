package br.com.andreyneto.testesantander

import android.content.Context
import java.util.*

fun Int.convertDpToPixel(ctx: Context): Int {
    val density = ctx.resources.displayMetrics.density
    return Math.round(this.toFloat() * density)
}

fun Any.toPercentage(): String {
    val value =  this as Double
    val s = java.lang.String.valueOf(Math.round(value * 100.0) / 100.0).replace(".", ",")
    return String.format("$s%%")
}