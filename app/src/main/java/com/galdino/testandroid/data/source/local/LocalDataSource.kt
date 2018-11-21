package com.galdino.testandroid.data.source.local

import android.content.Context
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.data.entity.investment.Risk
import com.galdino.testandroid.domain.interactor.investment.GetPeriods
import com.galdino.testandroid.domain.interactor.investment.GetRisks
import io.reactivex.Single

class LocalDataSource(val context: Context) : ILocalDataSource {
    override fun getPeriods(params: GetPeriods.Params): Single<List<PeriodModel>> {
        return Single.fromCallable {
            val moreInfo = params.moreInfo
            val list: MutableList<PeriodModel> = mutableListOf()
            list.add(PeriodModel(context.getString(R.string.on_month), moreInfo.month?.fund, moreInfo.month?.cdi))
            list.add(PeriodModel(context.getString(R.string.on_year), moreInfo.year?.fund, moreInfo.year?.cdi))
            list.add(PeriodModel(context.getString(R.string.on_twelve_month), moreInfo.months12?.fund, moreInfo.months12?.cdi))
            list
        }
    }

    override fun getRisks(params: GetRisks.Params): Single<List<Risk>> {
        return Single.fromCallable {
            val risks: MutableList<Risk> = mutableListOf()
            risks.add(Risk(1, R.color.colorRiskLevel1))
            risks.add(Risk(2, R.color.colorRiskLevel2))
            risks.add(Risk(3, R.color.colorRiskLevel3))
            risks.add(Risk(4, R.color.colorRiskLevel4))
            risks.add(Risk(5, R.color.colorRiskLevel5))

            for(risk in risks)
            {
                if(risk.riskId == params.idRiskSelected)
                {
                    risk.selected = true
                    break
                }
            }
            risks
        }
    }
}