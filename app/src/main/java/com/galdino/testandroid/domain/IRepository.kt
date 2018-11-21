package com.galdino.testandroid.domain

import com.galdino.testandroid.data.entity.CellResponseBody
import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.data.entity.investment.Risk
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.investment.GetInvestment
import com.galdino.testandroid.domain.interactor.investment.GetPeriods
import com.galdino.testandroid.domain.interactor.investment.GetRisks
import io.reactivex.Single

interface IRepository {
    fun getCell(params: GetCell.Params): Single<CellResponseBody>
    fun getInvestment(params: GetInvestment.Params): Single<InvestmentResponseBody>
    fun getPeriods(params: GetPeriods.Params): Single<List<PeriodModel>>
    fun getRisks(params: GetRisks.Params): Single<List<Risk>>
}