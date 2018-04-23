package com.vctapps.santanderchallenge.form.di

import com.vctapps.santanderchallenge.core.data.FloatingMountainApi
import com.vctapps.santanderchallenge.form.data.CellRepositoryImpl
import com.vctapps.santanderchallenge.form.domain.CellRepository
import com.vctapps.santanderchallenge.form.presentation.presenter.FormPresenter
import com.vctapps.santanderchallenge.form.presentation.presenter.FormPresenterImpl
import com.vctapps.santanderchallenge.form.presentation.view.FormView
import dagger.Module
import dagger.Provides

@FormScope
@Module
class FormModule {

    @Provides
    fun providesCellsRepository(floatingMountainApi: FloatingMountainApi): CellRepository =
            CellRepositoryImpl(floatingMountainApi)

    @Provides
    fun providesFormPresenter(formView: FormView,
                              cellRepository: CellRepository): FormPresenter =
            FormPresenterImpl(formView, cellRepository)

}