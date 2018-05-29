package andcordeiro.com.br.testeandroid.system.dagger

import andcordeiro.com.br.testeandroid.histories.contact.ContactModule
import andcordeiro.com.br.testeandroid.histories.investiment.InvestimentModule
import andcordeiro.com.br.testeandroid.system.retrofit.RetrofitApiModule
import android.app.Application

class App: Application() {

    private var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .contactModule(ContactModule())
                .investimentModule(InvestimentModule())
                .retrofitApiModule(RetrofitApiModule())
                .build()
    }

    fun getComponent(): ApplicationComponent{
        return component!!
    }


}