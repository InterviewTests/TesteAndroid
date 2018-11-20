package com.galdino.testandroid.domain.interactor.cell

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.UseCase

class CelUseCaseFactory (
        private val mRepository: IRepository,
        private val uiScheduler: UIScheduler,
        private val jobScheduler: JobScheduler): ICelUseCaseFactory {

    override fun loadForm(): UseCase<CellResponseBody, GetCell.Params> {
        return GetCell(mRepository, uiScheduler, jobScheduler)
    }
}