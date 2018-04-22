package com.vctapps.santanderchallenge.form.di

import com.vctapps.santanderchallenge.form.presentation.view.FormActivity
import com.vctapps.santanderchallenge.form.presentation.view.FormView
import dagger.Binds
import dagger.Module

@FormScope
@Module
abstract class FormModuleView {

    @Binds
    abstract fun bindFormView(activity: FormActivity): FormView

}