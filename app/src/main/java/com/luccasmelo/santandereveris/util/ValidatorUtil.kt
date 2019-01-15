package com.luccasmelo.santandereveris.util

fun String.isValidEmail():Boolean{
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


fun String.isValidPhone():Boolean{
    val unmask = this.unmask()
    return this.length >= 10
}