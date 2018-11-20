package com.galdino.testandroid.data.source

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.data.source.local.ILocalDataSource
import com.galdino.testandroid.data.source.remote.Api
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.investment.GetInvestment
import com.galdino.testandroid.domain.interactor.investment.GetPeriods
import io.reactivex.Single

class RepositoryImpl(private val dataSource: DataSource,
                     private val localDataSource: ILocalDataSource,
                     private val mMockedApi: Api): IRepository {
    override fun getCell(params: GetCell.Params): Single<CellResponseBody> {
        return dataSource.getCell(params)
    }

    override fun getInvestment(params: GetInvestment.Params): Single<InvestmentResponseBody> {
        return mMockedApi.getInvestment()
    }

    override fun getPeriods(params: GetPeriods.Params): Single<List<PeriodModel>> {
        return localDataSource.getPeriods(params)
    }
}