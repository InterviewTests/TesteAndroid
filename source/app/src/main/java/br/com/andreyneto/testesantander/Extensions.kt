package br.com.andreyneto.testesantander

import android.content.Context

fun Int.convertDpToPixel(ctx: Context): Int {
    val density = ctx.resources.displayMetrics.density
    return Math.round(this.toFloat() * density)
}