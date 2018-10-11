package br.com.santander.santanderinvestimento.contact.di

import br.com.santander.santanderinvestimento.contact.data.ContactRepositoryImpl
import br.com.santander.santanderinvestimento.contact.domain.ContactRepository
import br.com.santander.santanderinvestimento.contact.presentation.ContactContract
import br.com.santander.santanderinvestimento.contact.presentation.ContactPresenter
import org.koin.dsl.module.module

val contactModule = module {

    single<ContactRepository> { ContactRepositoryImpl(get()) }


    factory<ContactContract.Presenter> { ContactPresenter(get(), get(), get()) }
}