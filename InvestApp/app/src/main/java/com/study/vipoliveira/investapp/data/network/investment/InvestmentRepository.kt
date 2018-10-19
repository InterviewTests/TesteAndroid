package com.study.vipoliveira.investapp.data.network.investment

import com.study.vipoliveira.investapp.data.network.investment.api.InvestApi
import com.study.vipoliveira.investapp.data.network.investment.entities.InvestResponse
import io.reactivex.Single

class InvestmentRepository
constructor(private val investApi: InvestApi): InvestmentDataSource {
    override fun getInvest(): Single<InvestResponse> {
        return investApi.getInvestInfo()
    }
}