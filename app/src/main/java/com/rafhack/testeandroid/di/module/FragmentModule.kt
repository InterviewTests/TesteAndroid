package com.rafhack.testeandroid.di.module

import com.rafhack.testeandroid.form.FormPresenter
import com.rafhack.testeandroid.form.customCells.customCellType1.CustomCellType1Presenter
import com.rafhack.testeandroid.investment.InvestmentPresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideFormPresenter(): FormPresenter = FormPresenter()

    @Provides
    fun provideInvestmentPresenter(): InvestmentPresenter = InvestmentPresenter()

    @Provides
    fun provideCustomCellType1Presenter(): CustomCellType1Presenter = CustomCellType1Presenter()
}