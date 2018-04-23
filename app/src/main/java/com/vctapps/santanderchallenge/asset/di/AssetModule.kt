package com.vctapps.santanderchallenge.asset.di

import com.google.gson.Gson
import com.vctapps.santanderchallenge.asset.data.AssetRepositoryImpl
import com.vctapps.santanderchallenge.asset.domain.AssetRepository
import com.vctapps.santanderchallenge.asset.presentation.presenter.AssetPresenter
import com.vctapps.santanderchallenge.asset.presentation.presenter.AssetPresenterImpl
import com.vctapps.santanderchallenge.asset.presentation.view.AssetView
import com.vctapps.santanderchallenge.core.data.FloatingMountainApi
import dagger.Module
import dagger.Provides

@Module
class AssetModule {

    @Provides
    fun providesAssetPresenter(assetView: AssetView,
                               repository: AssetRepository): AssetPresenter =
            AssetPresenterImpl(assetView, repository)

    @Provides
    fun providesAssetRepository(floatingMountainApi: FloatingMountainApi,
                                gson: Gson): AssetRepository =
            AssetRepositoryImpl(floatingMountainApi, gson)

}