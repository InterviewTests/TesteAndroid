package com.study.vipoliveira.investapp.ui.investment

import com.study.vipoliveira.investapp.domain.InvestDomain
import com.study.vipoliveira.investapp.domain.SchedulersFacade
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class InvestPresenter (private val domain: InvestDomain,
                       private val facade: SchedulersFacade,
                       private val disposable: CompositeDisposable): InvestmentContract.Presenter {

    private lateinit var view: InvestmentContract.View

    override fun attach(view: InvestmentContract.View) {
        this.view = view
    }

    override fun getInvestments(){
        disposable.add(
                domain.requestInvestment()
                        .subscribeOn(facade.io())
                        .observeOn(facade.ui())
                        .doOnSubscribe {
                            _ -> view.displayLoadingUI()
                        }
                        .subscribe({
                            items -> view.hideLoadingUI()
                            view.updateInvestScreen(items.screen)
                        },
                                {
                                    error ->
                                    error.message?.let { view.displayError(it) }
                                })
        )
    }

    override fun clearDiposable() {
        disposable.clear()
    }
}