package com.galdino.testandroid.plataform.di

import com.galdino.testandroid.plataform.di.modules.appModule
import com.galdino.testandroid.plataform.di.modules.presenterModule
import com.galdino.testandroid.plataform.di.modules.useCaseModule

val appComponent = listOf(presenterModule, appModule, useCaseModule)