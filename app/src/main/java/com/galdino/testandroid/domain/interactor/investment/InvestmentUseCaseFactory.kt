package com.galdino.testandroid.domain.interactor.investment

import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.data.entity.investment.Risk
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.UseCase

class InvestmentUseCaseFactory(private val mRepository: IRepository,
                               private val uiScheduler: UIScheduler,
                               private val jobScheduler: JobScheduler): IinvestmentUseCaseFactory {
    override fun loadInvestment(): UseCase<InvestmentResponseBody, GetInvestment.Params> {
        return GetInvestment(mRepository, uiScheduler, jobScheduler)
    }

    override fun loadPeriods(): UseCase<List<PeriodModel>, GetPeriods.Params> {
        return GetPeriods(mRepository, uiScheduler, jobScheduler)
    }

    override fun loadRisks(): UseCase<List<Risk>, GetRisks.Params> {
        return GetRisks(mRepository,uiScheduler,jobScheduler)
    }
}