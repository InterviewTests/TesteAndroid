package br.com.santander.desafio.login

import dagger.Module
import dagger.Provides

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */
@Module
class LoginModule(var view: LoginMVP.View){

    @Provides
    fun provideLoginPresenter(): LoginMVP.Presenter{
        return  LoginPresenter(view)
    }

    @Provides
    fun provideLoginInteractor(): LoginMVP.Interactor{
        return LoginInteractor(LoginPresenter(view))
    }
}