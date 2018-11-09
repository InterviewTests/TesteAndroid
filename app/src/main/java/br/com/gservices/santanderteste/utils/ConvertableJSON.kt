package br.com.gservices.santanderteste.utils

import com.google.gson.Gson

interface ConvertableJSON {
    fun toJSON(): String = Gson().toJson(this)
}

inline fun <reified T: ConvertableJSON> String.toObject(): T = Gson().fromJson(this, T::class.java)