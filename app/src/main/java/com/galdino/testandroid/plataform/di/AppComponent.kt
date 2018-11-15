package com.galdino.testandroid.plataform.di

import com.galdino.testandroid.plataform.di.modules.interactorModule
import com.galdino.testandroid.plataform.di.modules.presenterModule

val appComponent = listOf(presenterModule, interactorModule)