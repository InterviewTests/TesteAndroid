package com.galdino.testandroid.plataform.di.modules


import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.domain.interactor.UseCaseFactory
import com.galdino.testandroid.plataform.executor.JobThread
import com.galdino.testandroid.plataform.executor.UIThread
import org.koin.dsl.module.module

val interactorModule = module {
    factory { JobThread() as JobScheduler }
    factory { UIThread() as UIScheduler }
    factory { UseCaseFactory() as UIScheduler }
    // Tdodo continuar
}