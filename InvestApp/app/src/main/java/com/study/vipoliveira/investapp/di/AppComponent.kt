package com.study.vipoliveira.investapp.di

import com.study.vipoliveira.investapp.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidSupportInjectionModule::class),
    (AppModule::class),
    (NetworkModule::class)])

interface AppComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
    fun inject(app: App)
}