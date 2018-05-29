package andcordeiro.com.br.testeandroid.histories.contact

import andcordeiro.com.br.testeandroid.system.retrofit.ApiService
import dagger.Module
import dagger.Provides

@Module
class ContactModule {

    @Provides
    fun provideContactPresenter(contactModel: ContactMVP.Model): ContactMVP.Presenter {
        return ContactPresenter(contactModel)
    }

    @Provides
    fun provideContactModel(api: ApiService): ContactMVP.Model {
        return ContactModel(api)
    }
}