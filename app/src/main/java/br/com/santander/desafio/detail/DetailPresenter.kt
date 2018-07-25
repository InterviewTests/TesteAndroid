package br.com.santander.desafio.detail

import android.arch.lifecycle.MutableLiveData
import br.com.santander.desafio.webservice.fund.ResponseFund
import javax.inject.Inject

class DetailPresenter(var view: DetailMVP.View) : DetailMVP.Presenter{


    @Inject
    lateinit var interactor: DetailMVP.Interactor

    override fun getFunc(): MutableLiveData<ResponseFund> {
        return interactor.getFund()
    }

    override fun initInteractor() {
        val detailComponent = DaggerDetailComponent.builder()
                .detailModule(DetailModule(view))
                .build()
        detailComponent.inject(this)
    }
}