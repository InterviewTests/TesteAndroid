package br.com.santander.desafio.detail

import android.arch.lifecycle.MutableLiveData
import br.com.santander.desafio.webservice.fund.ResponseFund

class DetailPresenter: DetailMVP.Presenter{

    val interactor: DetailInteractor = DetailInteractor()

    override fun getFunc(): MutableLiveData<ResponseFund> {
        return interactor.getFund()
    }
}