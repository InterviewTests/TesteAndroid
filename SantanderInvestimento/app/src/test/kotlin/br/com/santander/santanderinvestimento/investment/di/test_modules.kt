package br.com.santander.santanderinvestimento.investment.di

import br.com.santander.santanderinvestimento.di.appModule
import br.com.santander.santanderinvestimento.di.remoteDatasourceModule
import br.com.santander.santanderinvestimento.feature.investiment.di.investimentModule

val testApp = listOf(appModule, remoteDatasourceModule, investimentModule)

