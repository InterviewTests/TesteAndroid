package br.com.santander.santanderinvestimento.core.presentation

interface BasePresenter<V>{
    fun start()
    fun subscribe(view: V)
    fun unSubscribe()
    var view : V?
}