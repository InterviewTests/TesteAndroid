package santander.com.br.invest.di.component

import santander.com.br.invest.di.module.InvestmentModule
import santander.com.br.invest.di.module.RepositoryModule
import dagger.Component
import santander.com.br.invest.repository.InvestmentRepository
import santander.com.br.invest.ui.fragments.InvestmentFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        InvestmentModule::class,
        RepositoryModule::class)
)
interface InvestmentComponent {
  fun inject(fragment: InvestmentFragment)
}
