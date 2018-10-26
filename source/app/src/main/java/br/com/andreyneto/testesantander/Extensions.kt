package br.com.andreyneto.testesantander

import android.content.Context
import java.util.*

fun Int.convertDpToPixel(ctx: Context): Int {
    val density = ctx.resources.displayMetrics.density
    return Math.round(this.toFloat() * density)
}

fun Any.toPercentage(): String {
    val value =  this as Double
    val brasil = Locale("pt", "BR")
    return java.lang.String.format(brasil, "%.2f%%", value)
}