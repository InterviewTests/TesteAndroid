package br.com.santander.santanderinvestimento.contact.di

import br.com.santander.santanderinvestimento.core.di.appModule
import br.com.santander.santanderinvestimento.core.di.remoteDatasourceModule
import br.com.santander.santanderinvestimento.investiment.di.investimentModule
import br.com.santander.santanderinvestimento.util.TestSchedulerProvider
import br.com.santander.santanderinvestimento.util.rx.SchedulerProvider
import org.koin.dsl.module.applicationContext

val testRxModule = applicationContext {
    // provided components
    single <SchedulerProvider> {TestSchedulerProvider  ()}
}
//listOf(appModule, remoteDatasourceModule, investimentModule,  contactModule))
val testApp = listOf(appModule, remoteDatasourceModule, investimentModule,  contactModule)

