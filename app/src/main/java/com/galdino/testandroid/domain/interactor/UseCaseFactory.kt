package com.galdino.testandroid.domain.interactor

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.cell.GetCell

class UseCaseFactory (
        private val mRepository: IRepository,
        private val uiScheduler: UIScheduler,
        private val jobScheduler: JobScheduler):IUseCaseFactory {

    override fun loadForm(): UseCase<CellResponseBody, GetCell.Params> {
        return GetCell(mRepository, uiScheduler, jobScheduler)
    }
}