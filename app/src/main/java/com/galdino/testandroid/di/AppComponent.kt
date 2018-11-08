package com.galdino.testandroid.di

import com.galdino.testandroid.di.modules.interactorModule
import com.galdino.testandroid.di.modules.presenterModule

val appComponent = listOf(presenterModule, interactorModule)