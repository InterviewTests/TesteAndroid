package br.com.santander.desafio.login

import dagger.Component

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */

@Component(modules = arrayOf(LoginModule::class))
interface LoginComponent{
    fun inject(view: LoginFragment)
    fun inject(presenter: LoginPresenter)
}