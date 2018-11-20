package com.galdino.testandroid.plataform.views.investment

import com.galdino.testandroid.data.entity.investment.InvestmentResponseBody
import com.galdino.testandroid.domain.Observer
import com.galdino.testandroid.domain.interactor.investment.GetInvestment
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
            // continuar
        }
    }

    private fun loadScreenData() {

    }
}