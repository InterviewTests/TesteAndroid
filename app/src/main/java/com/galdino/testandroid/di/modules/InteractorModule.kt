package com.galdino.testandroid.di.modules

import com.galdino.testandroid.screens.form.FormContract
import com.galdino.testandroid.screens.form.FormInteractor
import org.koin.dsl.module.module

val interactorModule = module {
    factory { FormInteractor() as FormContract.Interactor }
}