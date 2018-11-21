package com.galdino.testandroid.data.source

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.investment.GetInvestment
import io.reactivex.Single

interface DataSource {
    fun getCell(params: GetCell.Params): Single<CellResponseBody>
    fun getInvestment(params: GetInvestment.Params): Single<InvestmentResponseBody>
}