package br.com.cesarsicas.stdandroidteste.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by julio on 4/22/18.
 */

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesContext(): Context {
        return application
    }
}