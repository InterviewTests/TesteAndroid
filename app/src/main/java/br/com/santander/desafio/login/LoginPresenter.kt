package br.com.santander.desafio.login

import android.arch.lifecycle.MutableLiveData
import br.com.santander.desafio.webservice.cells.ResponseCells

class LoginPresenter: LoginMVP.Presenter{

    val interactor: LoginInteractor = LoginInteractor()

    override fun getCells(): MutableLiveData<ResponseCells> {
        return interactor.getCells()
    }

}