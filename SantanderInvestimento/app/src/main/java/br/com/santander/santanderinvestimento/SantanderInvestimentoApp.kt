package br.com.santander.santanderinvestimento

import android.app.Application
import br.com.santander.santanderinvestimento.contact.di.contactModule
import br.com.santander.santanderinvestimento.core.di.appModule
import br.com.santander.santanderinvestimento.core.di.remoteDatasourceModule
import br.com.santander.santanderinvestimento.investiment.di.investimentModule
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin

/**
 * @author gustinoco
 */
class SantanderInvestimentoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(appModule, remoteDatasourceModule, investimentModule,  contactModule))
        Fabric.with(this, Crashlytics())
    }

    companion object {
        var instance: SantanderInvestimentoApp? = null
            private set
    }


}