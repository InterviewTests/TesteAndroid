package com.galdino.testandroid.domain.interactor.investment

import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.UseCase
import io.reactivex.Single

class GetInvestment(private val mRepository: IRepository,
                    uiScheduler: UIScheduler,
                    jobScheduler: JobScheduler): UseCase<InvestmentResponseBody,
        GetInvestment.Params>(uiScheduler,jobScheduler), IGetInvestment {

    override fun buildUseCaseObservable(params: Params): Single<InvestmentResponseBody> {
        return mRepository.getInvestment(params)
    }

    class Params()
}