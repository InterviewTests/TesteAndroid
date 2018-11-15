package com.galdino.testandroid.domain.interactor

import com.galdino.testandroid.domain.CellRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.model.Cell
import io.reactivex.Single

class GetCell(private val repository: CellRepository,
              uiScheduler: UIScheduler,
              jobScheduler: JobScheduler) : UseCase<Cell, GetCell.Params>(uiScheduler, jobScheduler) {
    override fun buildUseCaseObservable(params: Params): Single<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Params()
}