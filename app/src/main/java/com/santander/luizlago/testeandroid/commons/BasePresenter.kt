package com.santander.luizlago.testeandroid.commons

interface BasePresenter {
    fun onCreated()
    fun onResume()
    fun onPause()
    fun onDestroy()
}