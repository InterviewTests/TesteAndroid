package br.com.gservices.santanderteste.core.presenter

interface BasePresenter<V>{
    fun start()
    fun subscribe(view: V)
    fun unSubscribe()
    var view : V?
}