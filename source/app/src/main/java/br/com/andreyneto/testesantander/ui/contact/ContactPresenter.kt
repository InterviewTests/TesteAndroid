package br.com.andreyneto.testesantander.ui.contact

import br.com.andreyneto.testesantander.service.ApiService

class ContactPresenter(
        val view: ContactContract.View): ContactContract.Presenter {


    private val apiService = ApiService()

    init {
        view.setPresenter(this)
    }

    override fun getCells() {
        view.showCells(null)
    }

    override fun start() {
        getCells()
    }
}