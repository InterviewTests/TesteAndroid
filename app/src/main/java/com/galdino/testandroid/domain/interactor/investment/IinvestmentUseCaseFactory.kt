package com.galdino.testandroid.domain.interactor.investment

import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.domain.interactor.UseCase

interface IinvestmentUseCaseFactory {
    fun loadInvestment(): UseCase<InvestmentResponseBody, GetInvestment.Params>
    fun loadPeriods(): UseCase<List<PeriodModel>, GetPeriods.Params>
}