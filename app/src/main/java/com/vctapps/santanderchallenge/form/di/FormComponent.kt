package com.vctapps.santanderchallenge.form.di

import com.vctapps.santanderchallenge.form.presentation.view.FormActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FormScope
@Subcomponent(modules = [
    FormModule::class,
    FormModuleView::class
])
interface FormComponent: AndroidInjector<FormActivity> {

    @Subcomponent.Builder
    abstract class Builder: AndroidInjector.Builder<FormActivity>()

}