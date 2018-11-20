package com.galdino.testandroid.data.source.local

import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.domain.interactor.investment.GetPeriods
import io.reactivex.Single

interface ILocalDataSource {
    fun getPeriods(params: GetPeriods.Params): Single<List<PeriodModel>>
}