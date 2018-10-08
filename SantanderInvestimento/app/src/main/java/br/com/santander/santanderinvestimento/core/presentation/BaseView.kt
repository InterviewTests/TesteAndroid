package br.com.santander.santanderinvestimento.core.presentation

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}