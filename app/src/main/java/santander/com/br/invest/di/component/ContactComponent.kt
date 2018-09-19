package santander.com.br.invest.di.component

import dagger.Component
import santander.com.br.invest.di.module.ContactModule
import santander.com.br.invest.di.module.RepositoryModule
import santander.com.br.invest.ui.fragments.ContactFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ContactModule::class, RepositoryModule::class]
)
interface ContactComponent {
  fun inject(fragment: ContactFragment)
}
