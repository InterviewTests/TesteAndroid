package br.com.santander.desafio.content

import br.com.enzoteles.quickhelp.fragment.HelpManagerFragment
import br.com.santander.desafio.content.ContentFragment
import br.com.santander.desafio.detail.DetailFragment
import br.com.santander.desafio.login.LoginFragment
import dagger.Module
import dagger.Provides

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */
@Module
class ContentModule(){

    @Provides
    fun provideLoginFragment(): LoginFragment{
        return  LoginFragment()
    }

    @Provides
    fun provideDetailFragment(): DetailFragment {
        return  DetailFragment()
    }
}