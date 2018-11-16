package com.galdino.testandroid.plataform.di.modules

import com.galdino.testandroid.domain.interactor.IUseCaseFactory
import com.galdino.testandroid.domain.interactor.UseCaseFactory
import com.galdino.testandroid.domain.interactor.cell.GetCell
import com.galdino.testandroid.domain.interactor.cell.IGetCell
import org.koin.dsl.module.module


val useCaseModule = module {
    factory { UseCaseFactory(get(),get(),get()) as IUseCaseFactory }
    factory { GetCell(get(), get(), get()) as  IGetCell }
}