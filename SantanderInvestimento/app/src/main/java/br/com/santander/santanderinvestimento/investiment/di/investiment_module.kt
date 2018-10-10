package br.com.santander.santanderinvestimento.investiment.di

import br.com.santander.santanderinvestimento.investiment.data.InvestmentRepositoryImpl
import br.com.santander.santanderinvestimento.investiment.domain.InvestmentRepository
import br.com.santander.santanderinvestimento.investiment.presentation.InvestmentContract
import br.com.santander.santanderinvestimento.investiment.presentation.InvestmentPresenter
import org.koin.dsl.module.module


val investimentModule = module {

    single<InvestmentRepository> { InvestmentRepositoryImpl(get()) }


    factory<InvestmentContract.Presenter> { InvestmentPresenter(get(), get()) }
}