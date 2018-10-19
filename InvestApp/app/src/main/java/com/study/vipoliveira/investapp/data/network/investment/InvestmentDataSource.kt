package com.study.vipoliveira.investapp.data.network.investment

import com.study.vipoliveira.investapp.data.network.investment.entities.InvestResponse
import io.reactivex.Single

interface InvestmentDataSource {
    fun getInvest() : Single<InvestResponse>
}