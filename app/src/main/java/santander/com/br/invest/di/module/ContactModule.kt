package santander.com.br.invest.di.module

import dagger.Module
import dagger.Provides
import santander.com.br.invest.contract.ContactContract
import santander.com.br.invest.presenter.ContactPresenter

@Module
class ContactModule {

  @Provides
  fun provideContactPresenter(contactPresenter: ContactPresenter): ContactContract.Presenter = contactPresenter
}
