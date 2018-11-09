package br.com.gservices.santanderteste.core.modules

import br.com.gservices.santanderteste.pages.investments.data.InvestmentsImpl
import br.com.gservices.santanderteste.pages.investments.interfaces.ContractInvestmentInterface
import br.com.gservices.santanderteste.pages.investments.interfaces.InvestmentsInterface
import br.com.gservices.santanderteste.pages.investments.presenter.InvestmentsPresenter
import org.koin.dsl.module.module

val investimentsModule = module {

    single<InvestmentsInterface> { InvestmentsImpl(get()) }


    factory<ContractInvestmentInterface.Presenter> { InvestmentsPresenter(get(), get()) }
}