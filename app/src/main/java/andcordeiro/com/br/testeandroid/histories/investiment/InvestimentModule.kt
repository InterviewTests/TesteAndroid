package andcordeiro.com.br.testeandroid.histories.investiment

import andcordeiro.com.br.testeandroid.system.retrofit.ApiService
import dagger.Module
import dagger.Provides

@Module
class InvestimentModule {

    @Provides
    fun provideInvestimentPresenter(investimentModel: InvestimentMVP.Model):
            InvestimentMVP.Presenter {
        return InvestimentPresenter(investimentModel)
    }

    @Provides
    fun provideInvestimentModel(api: ApiService): InvestimentMVP.Model {
        return InvestimentModel(api)
    }
}