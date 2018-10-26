package br.com.santander.santanderinvestimento.investment.di

import br.com.santander.santanderinvestimento.core.di.appModule
import br.com.santander.santanderinvestimento.core.di.remoteDatasourceModule
import br.com.santander.santanderinvestimento.investiment.di.investimentModule

val testApp = listOf(appModule, remoteDatasourceModule, investimentModule)

