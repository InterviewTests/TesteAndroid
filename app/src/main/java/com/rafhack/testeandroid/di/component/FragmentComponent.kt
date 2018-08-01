package com.rafhack.testeandroid.di.component

import com.rafhack.testeandroid.di.module.FragmentModule
import com.rafhack.testeandroid.form.FormFragment
import com.rafhack.testeandroid.form.customCells.customCellType1.CustomCellType1
import com.rafhack.testeandroid.investment.InvestmentFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(FragmentModule::class)])
interface FragmentComponent {
    fun inject(formFragment: FormFragment)
    fun inject(investmentFragment: InvestmentFragment)
    fun inject(customCellType1: CustomCellType1)
}