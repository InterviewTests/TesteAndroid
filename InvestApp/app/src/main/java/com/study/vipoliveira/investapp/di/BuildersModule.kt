package com.study.vipoliveira.investapp.di

import com.study.vipoliveira.investapp.ui.MainActivity
import com.study.vipoliveira.investapp.ui.contact.ContactFormFragment
import com.study.vipoliveira.investapp.ui.investment.InvestFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [InvestmentModule::class])
    abstract fun bindInvestFragment(): InvestFragment

    @ContributesAndroidInjector(modules = [ContactModule::class])
    abstract fun bindContactFormFragment(): ContactFormFragment
}