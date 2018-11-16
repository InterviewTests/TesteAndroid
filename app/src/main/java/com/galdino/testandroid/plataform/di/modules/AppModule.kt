package com.galdino.testandroid.plataform.di.modules


import com.galdino.testandroid.data.source.DataSource
import com.galdino.testandroid.data.source.RepositoryImpl
import com.galdino.testandroid.data.source.remote.Api
import com.galdino.testandroid.data.source.remote.RemoteDataSource
import com.galdino.testandroid.data.source.remote.RemoteDataSourceMocked
import com.galdino.testandroid.domain.IRepository
import com.galdino.testandroid.domain.executor.JobScheduler
import com.galdino.testandroid.domain.executor.UIScheduler
import com.galdino.testandroid.plataform.executor.JobThread
import com.galdino.testandroid.plataform.executor.UIThread
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val appModule = module {
    factory { JobThread() as JobScheduler }
    factory { UIThread() as UIScheduler }
    factory { RepositoryImpl(get()) as IRepository }
    factory { RemoteDataSource(get()) as DataSource }
    factory { RemoteDataSourceMocked(androidApplication()) as Api }
}