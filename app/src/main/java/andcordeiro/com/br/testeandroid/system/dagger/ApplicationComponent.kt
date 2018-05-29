package andcordeiro.com.br.testeandroid.system.dagger

import andcordeiro.com.br.testeandroid.histories.contact.ContactFragment
import andcordeiro.com.br.testeandroid.histories.contact.ContactModule
import andcordeiro.com.br.testeandroid.histories.investiment.InvestimentFragment
import andcordeiro.com.br.testeandroid.histories.investiment.InvestimentModule
import andcordeiro.com.br.testeandroid.system.retrofit.RetrofitApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ContactModule::class,
        InvestimentModule::class, RetrofitApiModule::class))
interface ApplicationComponent {

    fun inject(target: ContactFragment)

    fun inject(target: InvestimentFragment)
}