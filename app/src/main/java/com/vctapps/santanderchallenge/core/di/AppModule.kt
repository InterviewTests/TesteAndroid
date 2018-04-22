package com.vctapps.santanderchallenge.core.di

import android.app.Application
import com.vctapps.santanderchallenge.form.di.FormComponent
import dagger.Module
import dagger.Provides

@Module(subcomponents = [
    FormComponent::class
])
class AppModule {

    @Provides
    fun providesApplicationContext(application: Application) = application.applicationContext

}