package br.com.santander.santanderinvestimento.feature.investiment.di

import br.com.santander.santanderinvestimento.feature.investiment.data.InvestmentRepositoryImpl
import br.com.santander.santanderinvestimento.feature.investiment.data.InvestmentRepository
import br.com.santander.santanderinvestimento.feature.investiment.presentation.InvestmentContract
import br.com.santander.santanderinvestimento.feature.investiment.presentation.InvestmentViewModel
import org.koin.dsl.module

val investimentModule = module {

    single<InvestmentRepository> { InvestmentRepositoryImpl(get(), get()) }


    factory { InvestmentViewModel(get()) }
}