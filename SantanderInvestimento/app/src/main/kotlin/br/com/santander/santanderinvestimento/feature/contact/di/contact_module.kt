package br.com.santander.santanderinvestimento.feature.contact.di

import br.com.santander.santanderinvestimento.feature.contact.data.ContactRepositoryImpl
import br.com.santander.santanderinvestimento.feature.contact.data.ContactRepository
import br.com.santander.santanderinvestimento.feature.contact.presentation.ContactContract
import br.com.santander.santanderinvestimento.feature.contact.presentation.ContactViewModel
import org.koin.dsl.module

val contactModule = module {

    single<ContactRepository> { ContactRepositoryImpl(get(), get()) }
    factory { ContactViewModel(get()) }
}