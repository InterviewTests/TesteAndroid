package com.galdino.testandroid.domain.interactor.investment

import com.galdino.testandroid.data.entity.investment.Risk
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.UseCase
import io.reactivex.Single

class GetRisks(private val mRepository: IRepository,
               uiScheduler: UIScheduler,
               jobScheduler: JobScheduler):
        UseCase<List<Risk>, GetRisks.Params>(uiScheduler, jobScheduler) {

    override fun buildUseCaseObservable(params: Params): Single<List<Risk>> {
        return mRepository.getRisks(params)
    }

    class Params(val idRiskSelected: Int)
}