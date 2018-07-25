package br.com.santander.desafio.detail

import dagger.Component

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */

@Component(modules = arrayOf(DetailModule::class))
interface DetailComponent{
    fun inject(view: DetailFragment)
    fun inject(presenter: DetailPresenter)
}