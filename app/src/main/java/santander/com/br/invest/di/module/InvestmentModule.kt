package santander.com.br.invest.di.module

import santander.com.br.invest.contract.InvestmentContract
import santander.com.br.invest.presenter.InvestmentPresenter
import dagger.Module
import dagger.Provides

@Module
class InvestmentModule {

  @Provides
  fun provideInvestmentPresenter(investmentPresenter: InvestmentPresenter): InvestmentContract.Presenter = investmentPresenter
}
