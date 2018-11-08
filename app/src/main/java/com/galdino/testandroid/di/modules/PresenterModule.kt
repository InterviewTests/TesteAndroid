package com.galdino.testandroid.di.modules

import com.galdino.testandroid.screens.form.FormContract
import com.galdino.testandroid.screens.form.FormInteractor
import com.galdino.testandroid.screens.form.FormPresenter
import org.koin.dsl.module.module

val presenterModule = module{
    factory { FormPresenter(get()) as FormContract.Presenter }
}