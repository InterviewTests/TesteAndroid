package com.vctapps.santanderchallenge.core.di

import android.app.Application
import com.vctapps.santanderchallenge.core.SantanderChallengeApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewBuilder::class,
    AppModule::class,
    NetworkModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: SantanderChallengeApplication)

}