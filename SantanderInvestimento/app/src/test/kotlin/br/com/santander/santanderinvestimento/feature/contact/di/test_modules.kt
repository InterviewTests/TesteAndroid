package br.com.santander.santanderinvestimento.feature.contact.di

import br.com.santander.santanderinvestimento.di.appModule
import br.com.santander.santanderinvestimento.di.remoteDatasourceModule

val testApp = listOf(appModule, remoteDatasourceModule, contactModule)

