package br.com.santander.desafio.login

import android.arch.lifecycle.MutableLiveData
import br.com.enzoteles.quickhelp.log.HelpLog
import br.com.finners.marketpay.api.card.CellsApi
import br.com.santander.desafio.webservice.cells.CellsItem
import br.com.santander.desafio.webservice.cells.ResponseCells
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginInteractor: LoginMVP.Interactor{


    override fun getCells() : MutableLiveData<ResponseCells> {
        var data: MutableLiveData<ResponseCells>?=  MutableLiveData<ResponseCells>()
        val api = CellsApi()
        api.getCellsList()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe ({ response ->
                    data?.value = response as ResponseCells
                }, { e ->
                    HelpLog.error("${e.message}")
                })
        return data!!
    }

}