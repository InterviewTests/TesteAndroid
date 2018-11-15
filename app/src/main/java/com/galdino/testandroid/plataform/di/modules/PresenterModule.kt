package com.galdino.testandroid.plataform.di.modules

import com.galdino.testandroid.plataform.views.home.HomeContract
import com.galdino.testandroid.plataform.views.home.HomePresenter
import org.koin.dsl.module.module

val presenterModule = module{
    factory { HomePresenter() as HomeContract.Presenter }
}