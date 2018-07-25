package br.com.santander.desafio.detail

import android.arch.lifecycle.MutableLiveData
import br.com.enzoteles.quickhelp.log.HelpLog
import br.com.finners.marketpay.api.card.ScreenApi
import br.com.santander.desafio.webservice.fund.ResponseFund
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailInteractor(var callback: DetailMVP.Presenter) : DetailMVP.Interactor{

    override fun getFund() : MutableLiveData<ResponseFund> {
        var data: MutableLiveData<ResponseFund>?=  MutableLiveData<ResponseFund>()
        val api = ScreenApi()
        api.getScreenList()
                ?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe ({ response ->
                    data?.value = response as ResponseFund
                }, { e ->
                    HelpLog.error("${e.message}")
                })
        return data!!
    }
}