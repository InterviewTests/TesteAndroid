package br.com.gservices.santanderteste

import android.app.Application
import br.com.gservices.santanderteste.core.modules.appModule
import br.com.gservices.santanderteste.core.modules.contactsModule
import br.com.gservices.santanderteste.core.modules.investimentsModule
import br.com.gservices.santanderteste.core.modules.networkModule
import org.koin.android.ext.android.startKoin

class SantanderApp : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, networkModule, investimentsModule,  contactsModule))
    }

    companion object {
        var instance: SantanderApp? = null
            private set
    }
}