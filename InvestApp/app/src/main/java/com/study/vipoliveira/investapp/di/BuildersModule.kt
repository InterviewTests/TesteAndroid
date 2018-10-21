package com.study.vipoliveira.investapp.di

import com.study.vipoliveira.investapp.ui.MainActivity
import com.study.vipoliveira.investapp.ui.investment.InvestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector(modules = [InvestmentModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [InvestmentModule::class])
    abstract fun bindInvestFragment(): InvestFragment
}