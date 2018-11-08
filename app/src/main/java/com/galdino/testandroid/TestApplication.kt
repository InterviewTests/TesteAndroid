package com.galdino.testandroid

import android.app.Application
import com.galdino.testandroid.di.appComponent
import org.koin.android.ext.android.startKoin

class TestApplication: Application()
{
    override fun onCreate() {
        super.onCreate()
        startKoin(this, appComponent)
    }
}