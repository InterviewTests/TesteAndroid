package br.com.santander.desafio.detail

import android.arch.lifecycle.MutableLiveData
import br.com.santander.desafio.webservice.fund.DownInfoItem
import br.com.santander.desafio.webservice.fund.InfoItem
import br.com.santander.desafio.webservice.fund.ResponseFund

interface DetailMVP{
    interface View{
        fun initUI()
        fun initDate()
        fun setDataView(response: ResponseFund)
        fun setRecyclerviewInfo(listInfo: MutableList<InfoItem>)
        fun setRecyclerviewDownInfo(listDownInfo: MutableList<DownInfoItem>)
    }
    interface Presenter{
        fun getFunc(): MutableLiveData<ResponseFund>
    }
    interface Interactor{
        fun getFund(): MutableLiveData<ResponseFund>
    }
}