package com.galdino.testandroid.plataform.di.modules

import com.galdino.testandroid.domain.interactor.cell.CelUseCaseFactory
import com.galdino.testandroid.domain.interactor.cell.ICelUseCaseFactory
import com.galdino.testandroid.domain.interactor.investment.IinvestmentUseCaseFactory
import com.galdino.testandroid.domain.interactor.investment.InvestmentUseCaseFactory
import org.koin.dsl.module.module


val useCaseModule = module {
    factory { CelUseCaseFactory(get(), get(), get()) as ICelUseCaseFactory }
    factory { InvestmentUseCaseFactory(get(), get(), get()) as IinvestmentUseCaseFactory }
}