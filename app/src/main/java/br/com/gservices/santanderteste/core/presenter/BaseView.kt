package br.com.gservices.santanderteste.core.presenter

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
    fun showMessage(message: String)
    fun showLoading(active: Boolean)
}