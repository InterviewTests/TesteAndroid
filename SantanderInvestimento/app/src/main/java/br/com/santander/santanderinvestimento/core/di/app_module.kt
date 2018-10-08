package br.com.santander.santanderinvestimento.core.di

import br.com.santander.santanderinvestimento.util.rx.ApplicationSchedulerProvider
import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider
import org.koin.dsl.module.module

val appModule = module {

    // Rx Schedulers
    single<SchedulerProvider> { ApplicationSchedulerProvider() }

}