package com.vctapps.santanderchallenge.core.di

import android.app.Activity
import com.vctapps.santanderchallenge.form.di.FormComponent
import com.vctapps.santanderchallenge.form.presentation.view.FormActivity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(FormActivity::class)
    abstract fun bindLoginActivity(builder: FormComponent.Builder): AndroidInjector.Factory<out Activity>

}