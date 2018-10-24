package com.fuinha11.test.testeandroid.util.interfaces


interface SuccessFailCallback<T, R> : SimpleCallback<T> {
    fun onFailure(data: R)
}