package com.galdino.testandroid.domain.interactor.investment

import com.galdino.testandroid.data.entity.investment.MoreInfo
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.UseCase
import io.reactivex.Single

class GetPeriods(private val mRepository: IRepository,
                 uiScheduler: UIScheduler,
                 jobScheduler: JobScheduler):
        UseCase<List<PeriodModel>, GetPeriods.Params>(uiScheduler,jobScheduler){
    override fun buildUseCaseObservable(params: Params): Single<List<PeriodModel>> {
        return mRepository.getPeriods(params)
    }

    class Params(val moreInfo: MoreInfo)
}