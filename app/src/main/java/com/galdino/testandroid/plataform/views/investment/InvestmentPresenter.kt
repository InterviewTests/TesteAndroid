package com.galdino.testandroid.plataform.views.investment

import com.galdino.testandroid.data.entity.investment.*
import com.galdino.testandroid.domain.Observer
import com.galdino.testandroid.domain.interactor.investment.GetInvestment
import com.galdino.testandroid.domain.interactor.investment.GetPeriods
import com.galdino.testandroid.domain.interactor.investment.GetRisks
import com.galdino.testandroid.domain.interactor.investment.IinvestmentUseCaseFactory
import com.galdino.testandroid.mvp.BasePresenter

class InvestmentPresenter(private val useCaseFactory: IinvestmentUseCaseFactory): BasePresenter<InvestmentContract.View>(), InvestmentContract.Presenter {
    private var mInvestmentResponseBody: InvestmentResponseBody? = null
    override fun loadInvestment() {
        if(mInvestmentResponseBody == null)
        {
            val loadInvestment = useCaseFactory.loadInvestment()
            loadInvestment.execute(object: Observer<InvestmentResponseBody>(){
                override fun onStart() {
                    mView?.onLoading(true)
                }

                override fun onSuccess(t: InvestmentResponseBody) {
                    mView?.onLoading(false)
                    mInvestmentResponseBody = t
                    loadScreenData()
                }

                override fun onError(e: Throwable) {
                    if (e.message == null) {
                        mView?.showDefaultErrorOnLoadInvestment()
                    } else {
                        mView?.showError(e.message!!)
                    }
                    mView?.onLoading(false)
                }
            }, GetInvestment.Params())
        }
        else{
            loadScreenData()
        }
    }

    private fun loadScreenData() {
        mView?.hideBackgroundLoading()
        mInvestmentResponseBody?.screen?.let {screenInvestment->
            mView?.loadScreenData(screenInvestment)
            screenInvestment.moreInfo?.let {
                loadPeriods(it)
            }
            screenInvestment.risk?.let {
                loadRisks(it)
            }
            screenInvestment.info?.let {
                mView?.loadInfoList(it)
            }
            screenInvestment.downInfo?.let {
                mView?.loadDownList(it)
            }

        }
    }

    private fun loadRisks(riskId: Int) {
        val loadRisks = useCaseFactory.loadRisks()
        loadRisks.execute(object: Observer<List<Risk>>(){
            override fun onStart() {
                mView?.onLoading(true)
            }
            override fun onError(e: Throwable) {
                if (e.message == null) {
                    mView?.showDefaultErrorOnLoadRisks()
                } else {
                    mView?.showError(e.message!!)
                }
                mView?.onLoading(false)
            }

            override fun onSuccess(t: List<Risk>) {
                mView?.loadRisksList(t)
                mView?.onLoading(false)
            }
        },GetRisks.Params(riskId))
    }

    private fun loadPeriods(moreInfo: MoreInfo) {
        val loadPeriods = useCaseFactory.loadPeriods()
        loadPeriods.execute(object: Observer<List<PeriodModel>>(){
            override fun onStart() {
                mView?.onLoading(true)
            }

            override fun onError(e: Throwable) {
                if (e.message == null) {
                    mView?.showDefaultErrorOnLoadMoreInfo()
                } else {
                    mView?.showError(e.message!!)
                }
                mView?.onLoading(false)
            }

            override fun onSuccess(t: List<PeriodModel>) {
                mView?.loadMoreInfoList(t)
                mView?.onLoading(false)
            }
        },GetPeriods.Params(moreInfo))
    }

    override fun onDownloadClicked(downInfo: DownInfo) {
        mView?.downloading()
    }

    override fun onInvestClicked() {
        mView?.invest()
    }
}