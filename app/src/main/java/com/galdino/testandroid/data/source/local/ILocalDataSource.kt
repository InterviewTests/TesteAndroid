package com.galdino.testandroid.data.source.local

import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.data.entity.investment.Risk
import com.galdino.testandroid.domain.interactor.investment.GetPeriods
import com.galdino.testandroid.domain.interactor.investment.GetRisks
import io.reactivex.Single

interface ILocalDataSource {
    fun getPeriods(params: GetPeriods.Params): Single<List<PeriodModel>>
    fun getRisks(params: GetRisks.Params): Single<List<Risk>>
}