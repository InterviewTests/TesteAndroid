package com.fuinha11.test.testeandroid.util.interfaces

import java.io.Serializable

interface SimpleCallback<T> : Serializable{
    fun onSuccess(data: T)
}