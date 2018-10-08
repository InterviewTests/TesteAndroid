package br.com.santander.santanderinvestimento

import android.app.Application
import br.com.santander.santanderinvestimento.core.di.appModule
import br.com.santander.santanderinvestimento.core.di.remoteDatasourceModule
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.startKoin

/**
 * @author gustinoco
 */
class MovieGuideApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule, remoteDatasourceModule))
        Fabric.with(this, Crashlytics())
    }

}