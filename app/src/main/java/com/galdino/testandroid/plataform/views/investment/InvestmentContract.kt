package com.galdino.testandroid.plataform.views.investment

import com.galdino.testandroid.data.entity.investment.*
import com.galdino.testandroid.mvp.Contract

interface InvestmentContract {
    interface View: Contract.View{
        fun onLoading(isLoading: Boolean)
        fun showDefaultErrorOnLoadInvestment()
        fun showDefaultErrorOnLoadMoreInfo()
        fun showDefaultErrorOnLoadRisks()
        fun showError(message: String)
        fun loadInfoList(infoList: List<Info>)
        fun loadDownList(downsInfoList: List<DownInfo>)
        fun loadScreenData(screenInvestment: ScreenInvestment)
        fun downloading()
        fun invest()
        fun loadMoreInfoList(periodList: List<PeriodModel>)
        fun loadRisksList(risks: List<Risk>)
        fun hideBackgroundLoading()
        fun shareInvestmentByWhatsApp(msg: String)
    }

    interface Presenter: Contract.Presenter<View>{
        fun loadInvestment()
        fun onDownloadClicked(downInfo: DownInfo)
        fun onInvestClicked()
        fun shareInvestmentClicked()
    }
}