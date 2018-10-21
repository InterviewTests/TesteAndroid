package com.study.vipoliveira.investapp.di

import com.study.vipoliveira.investapp.data.network.investment.InvestmentDataSource
import com.study.vipoliveira.investapp.data.network.investment.InvestmentRepository
import com.study.vipoliveira.investapp.data.network.investment.api.InvestApi
import com.study.vipoliveira.investapp.domain.InvestDomain
import com.study.vipoliveira.investapp.ui.investment.InvestPresenter
import com.study.vipoliveira.investapp.ui.investment.InvestmentContract
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class InvestmentModule {
    @Provides
    fun provideInvestmentDataSource(investApi: InvestApi): InvestmentDataSource {
        return InvestmentRepository(investApi)
    }

    @Provides
    fun provideInvestDomain(dataSource: InvestmentDataSource): InvestDomain {
        return InvestDomain(dataSource)
    }

    @Provides
    fun provideInvestPresenter(domain: InvestDomain, disposable: CompositeDisposable): InvestmentContract.Presenter {
        return InvestPresenter(domain, disposable)
    }
}