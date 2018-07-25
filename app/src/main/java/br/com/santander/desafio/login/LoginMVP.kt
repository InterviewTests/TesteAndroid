package br.com.santander.desafio.login

import android.arch.lifecycle.MutableLiveData
import android.widget.EditText
import br.com.santander.desafio.webservice.cells.CellsItem
import br.com.santander.desafio.webservice.cells.ResponseCells

interface LoginMVP{

    interface View {
        fun verificationCells(responses: ResponseCells?)
        fun validationCampos(id: CellsItem?)
        fun initUI()
        fun initDate()
        fun inputPhone()
        fun validationEditText(lg_et_name: EditText?, lg_et_email: EditText?)
    }

    interface Presenter {
        fun getCells(): MutableLiveData<ResponseCells>
    }

    interface Interactor{
        fun getCells(): MutableLiveData<ResponseCells>
    }
}