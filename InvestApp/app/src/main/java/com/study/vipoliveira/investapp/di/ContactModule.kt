package com.study.vipoliveira.investapp.di

import com.study.vipoliveira.investapp.data.network.contact.ContactDataSource
import com.study.vipoliveira.investapp.data.network.contact.ContactRepository
import com.study.vipoliveira.investapp.data.network.contact.api.ContactApi
import com.study.vipoliveira.investapp.data.network.investment.InvestmentDataSource
import com.study.vipoliveira.investapp.data.network.investment.InvestmentRepository
import com.study.vipoliveira.investapp.data.network.investment.api.InvestApi
import com.study.vipoliveira.investapp.domain.ContactDomain
import com.study.vipoliveira.investapp.domain.InvestDomain
import com.study.vipoliveira.investapp.ui.contact.ContactFormContract
import com.study.vipoliveira.investapp.ui.contact.ContactFormPresenter
import com.study.vipoliveira.investapp.ui.investment.InvestPresenter
import com.study.vipoliveira.investapp.ui.investment.InvestmentContract
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ContactModule {
    @Provides
    fun provideContactDataSource(contactApi: ContactApi): ContactDataSource {
        return ContactRepository(contactApi)
    }

    @Provides
    fun provideContactDomain(dataSource: ContactDataSource): ContactDomain {
        return ContactDomain(dataSource)
    }

    @Provides
    fun provideContactFormPresenter(domain: ContactDomain, disposable: CompositeDisposable): ContactFormContract.Presenter {
        return ContactFormPresenter(domain, disposable)
    }
}