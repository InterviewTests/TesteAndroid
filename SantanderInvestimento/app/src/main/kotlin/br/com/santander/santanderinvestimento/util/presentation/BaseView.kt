package br.com.santander.santanderinvestimento.util.presentation

interface BaseView<out T : BasePresenter<*>> {
    fun showMessage(message: String)
    fun showLoading(active: Boolean)
}