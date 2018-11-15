package com.galdino.testandroid.plataform.di.modules

import com.galdino.testandroid.plataform.views.home.HomeContract
import com.galdino.testandroid.domain.interactor.HomeInteractor
import org.koin.dsl.module.module

val interactorModule = module {
    factory { HomeInteractor() as HomeContract.Interactor }
}