package com.galdino.testandroid.domain.interactor

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.model.Cell
import io.reactivex.Single

class GetCell(private val repository: IRepository,
              uiScheduler: UIScheduler,
              jobScheduler: JobScheduler) : UseCase<CellResponseBody, GetCell.Params>(uiScheduler, jobScheduler) {
    override fun buildUseCaseObservable(params: Params): Single<CellResponseBody> {
        return Single.just(repository.getCell())
    }

    class Params()
}