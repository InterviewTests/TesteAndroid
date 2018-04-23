package com.vctapps.santanderchallenge.asset.di

import com.vctapps.santanderchallenge.asset.presentation.view.AssetFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent(modules = [
    AssetModule::class,
    AssetModuleView::class
])
interface AssetComponent: AndroidInjector<AssetFragment> {

    @Subcomponent.Builder
    abstract class Builder(): AndroidInjector.Builder<AssetFragment>()

}