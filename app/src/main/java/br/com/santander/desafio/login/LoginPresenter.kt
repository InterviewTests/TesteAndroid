package br.com.santander.desafio.login

import android.arch.lifecycle.MutableLiveData
import br.com.santander.desafio.webservice.cells.ResponseCells
import javax.inject.Inject

class LoginPresenter(var view: LoginMVP.View) : LoginMVP.Presenter{


    @Inject
    lateinit var interactor: LoginMVP.Interactor

    override fun getCells(): MutableLiveData<ResponseCells> {
        return interactor.getCells()
    }

    override fun initInteractor() {
        val loginComponent = DaggerLoginComponent.builder()
                .loginModule(LoginModule(view))
                .build()
        loginComponent.inject(this)
    }

}