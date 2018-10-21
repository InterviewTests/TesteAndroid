package com.study.vipoliveira.investapp.domain

import com.study.vipoliveira.investapp.data.network.investment.InvestmentDataSource
import com.study.vipoliveira.investapp.data.network.investment.entities.InvestResponse
import io.reactivex.Single

class InvestDomain(private val dataSource: InvestmentDataSource) {
    fun requestInvestment(): Single<InvestResponse>{
       return dataSource.getInvest().map{it}
    }
}