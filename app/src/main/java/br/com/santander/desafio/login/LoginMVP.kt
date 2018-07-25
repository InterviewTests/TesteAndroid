package br.com.santander.desafio.login

import android.arch.lifecycle.MutableLiveData
import br.com.santander.desafio.webservice.cells.CellsItem
import br.com.santander.desafio.webservice.cells.ResponseCells

interface LoginMVP{

    interface View {
        fun verificationCells(responses: ResponseCells?)
        fun validationCampos(id: CellsItem?)
        fun initUI()
        fun initDate()
    }

    interface Presenter {
        fun getCells(): MutableLiveData<ResponseCells>
    }

    interface Interactor{
        fun getCells(): MutableLiveData<ResponseCells>
    }
}