package br.com.santander.santanderinvestimento

import android.app.Application
import br.com.santander.santanderinvestimento.feature.contact.di.contactModule
import br.com.santander.santanderinvestimento.di.appModule
import br.com.santander.santanderinvestimento.di.remoteDatasourceModule
import br.com.santander.santanderinvestimento.feature.investiment.di.investimentModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

/**
 * @author gustinoco
 */
class SantanderInvestimentoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(applicationContext)
            modules(listOf(appModule, remoteDatasourceModule, investimentModule,  contactModule))
            // Fabric.with(this, Crashlytics())
        }
    }

    companion object {
        var instance: SantanderInvestimentoApp? = null
            private set
    }


}