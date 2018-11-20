package com.galdino.testandroid.plataform.views.investment

import com.galdino.testandroid.data.entity.investment.DownInfo
import com.galdino.testandroid.data.entity.investment.Info
import com.galdino.testandroid.data.entity.investment.PeriodModel
import com.galdino.testandroid.data.entity.investment.ScreenInvestment
import com.galdino.testandroid.mvp.Contract

interface InvestmentContract {
    interface View: Contract.View{
        fun onLoading(isLoading: Boolean)
        fun showDefaultErrorOnLoadInvestment()
        fun showError(message: String)
        fun loadInfoList(infoList: List<Info>)
        fun loadDownList(downsInfoList: List<DownInfo>)
        fun loadScreenData(screenInvestment: ScreenInvestment)
        fun downloading()
        fun invest()
        fun showDefaultErrorOnLoadMoreInfo()
        fun loadMoreInfoList(periodList: List<PeriodModel>)
    }

    interface Presenter: Contract.Presenter<View>{
        fun loadInvestment()
        fun onDownloadClicked(downInfo: DownInfo)
        fun onInvestClicked()
    }
}