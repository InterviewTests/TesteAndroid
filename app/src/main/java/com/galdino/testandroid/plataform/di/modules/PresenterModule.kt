package com.galdino.testandroid.plataform.di.modules

import com.galdino.testandroid.plataform.views.contact.ContactContract
import com.galdino.testandroid.plataform.views.contact.ContactPresenter
import com.galdino.testandroid.plataform.views.home.HomeContract
import com.galdino.testandroid.plataform.views.home.HomePresenter
import com.galdino.testandroid.plataform.views.investment.InvestmentContract
import com.galdino.testandroid.plataform.views.investment.InvestmentPresenter
import org.koin.dsl.module.module

val presenterModule = module{
    factory { HomePresenter() as HomeContract.Presenter }
    factory { ContactPresenter() as ContactContract.Presenter }
    factory { InvestmentPresenter() as InvestmentContract.Presenter }
}