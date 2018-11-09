package br.com.gservices.santanderteste.core.modules

import br.com.gservices.santanderteste.SantanderApp
import br.com.gservices.santanderteste.reactive.SchedulerImpl
import br.com.gservices.santanderteste.reactive.SchedulerInterface
import org.koin.dsl.module.module

val appModule = module {
    single<SchedulerInterface> { SchedulerImpl() }
    single { SantanderApp.instance!! }
}