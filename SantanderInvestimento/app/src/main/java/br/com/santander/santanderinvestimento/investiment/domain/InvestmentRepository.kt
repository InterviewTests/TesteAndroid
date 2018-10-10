package br.com.santander.santanderinvestimento.investiment.domain

import br.com.santander.santanderinvestimento.investiment.data.entity.InvestmentResponse
import br.com.santander.santanderinvestimento.investiment.domain.entity.Investment
import io.reactivex.Flowable


interface InvestmentRepository {

    fun getInvestment(): Flowable<Investment>

}