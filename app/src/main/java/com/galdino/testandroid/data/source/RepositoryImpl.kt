package com.galdino.testandroid.data.source

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.data.source.remote.Api
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.investment.GetInvestment
import io.reactivex.Single

class RepositoryImpl(private val dataSource: DataSource,
                     private val mMockedApi: Api): IRepository {
    override fun getCell(params: GetCell.Params): Single<CellResponseBody> {
        return dataSource.getCell(params)
    }

    override fun getInvestment(params: GetInvestment.Params): Single<InvestmentResponseBody> {
        return mMockedApi.getInvestment()
    }
}