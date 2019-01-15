package com.luccasmelo.santandereveris.util

fun String.unmask():String{
    return replace("[a*,0-9]".toRegex(),"")
}

fun String.toPercent():String{
    return "${replace(".",",")}%"
}