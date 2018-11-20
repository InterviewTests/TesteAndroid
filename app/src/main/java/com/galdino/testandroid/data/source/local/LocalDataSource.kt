package com.galdino.testandroid.data.source.local

import android.content.Context
import com.galdino.testandroid.R
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.domain.interactor.investment.GetPeriods
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
}