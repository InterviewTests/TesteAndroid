package br.com.santander.santanderinvestimento.contact.di

import br.com.santander.santanderinvestimento.core.di.appModule
import br.com.santander.santanderinvestimento.core.di.remoteDatasourceModule

val testApp = listOf(appModule, remoteDatasourceModule, contactModule)

