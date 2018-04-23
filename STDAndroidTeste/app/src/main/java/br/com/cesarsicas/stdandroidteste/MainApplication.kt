package br.com.cesarsicas.stdandroidteste

import android.app.Application
import br.com.cesarsicas.stdandroidteste.modules.AndroidModule

/**
 * Created by julio on 4/22/18.
 */
class MainApplication : android.app.Application()  {
     var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
    }


    public fun getComponent(): AppComponent? {
        return appComponent
    }

}