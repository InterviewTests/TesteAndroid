package br.com.gservices.santanderteste.core.modules

import br.com.gservices.santanderteste.pages.contacts.data.ContactsImpl
import br.com.gservices.santanderteste.pages.contacts.interfaces.ContactsInterface
import br.com.gservices.santanderteste.pages.contacts.interfaces.ContractContactsInterface
import br.com.gservices.santanderteste.pages.contacts.presenter.ContactsPresenter
import org.koin.dsl.module.module
val contactsModule = module {
    single<ContactsInterface> { ContactsImpl(get()) }
    factory<ContractContactsInterface.Presenter> { ContactsPresenter(get(), get()) }
}