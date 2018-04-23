package br.com.cesarsicas.stdandroidteste

import br.com.cesarsicas.stdandroidteste.modules.AndroidModule
import br.com.cesarsicas.stdandroidteste.mvp.form.FormActivity
import br.com.cesarsicas.stdandroidteste.mvp.home.HomeActivity
import br.com.cesarsicas.stdandroidteste.mvp.home.contact.ContactFragment
import br.com.cesarsicas.stdandroidteste.mvp.home.investment.InvestmentFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by julio on 4/22/18.
 */

@Component(modules = arrayOf(AndroidModule::class))
@Singleton
interface AppComponent {

    fun inject(formActivity: FormActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(contactFragment: ContactFragment)
    fun inject (investmentFragment: InvestmentFragment)
}