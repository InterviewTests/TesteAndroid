package br.com.santander.desafio.main

import dagger.Component

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */

@Component(modules = arrayOf(MainModule::class))
interface MainComponent{
    fun inject(view: MainActivity)
}