package com.vctapps.santanderchallenge.asset.di

import com.vctapps.santanderchallenge.asset.presentation.view.AssetFragment
import com.vctapps.santanderchallenge.asset.presentation.view.AssetView
import dagger.Binds
import dagger.Module

@Module
abstract class AssetModuleView {

    @Binds
    abstract fun bindAssetView(fragment: AssetFragment): AssetView

}