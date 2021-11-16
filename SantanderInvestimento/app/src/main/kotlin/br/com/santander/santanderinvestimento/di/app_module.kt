package br.com.santander.santanderinvestimento.di

import br.com.santander.santanderinvestimento.SantanderInvestimentoApp
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val appModule = module {

    // Rx Schedulers
    single { SantanderInvestimentoApp.instance!! }

    single<CoroutineContext> { Dispatchers.IO }
}