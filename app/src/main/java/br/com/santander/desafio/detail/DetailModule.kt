package br.com.santander.desafio.detail

import dagger.Module
import dagger.Provides

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */
@Module
class DetailModule(var view: DetailMVP.View){

    @Provides
    fun provideDetailPresenter(): DetailMVP.Presenter{
        return  DetailPresenter(view)
    }

    @Provides
    fun provideDetailInteractor(): DetailMVP.Interactor{
        return DetailInteractor(DetailPresenter(view))
    }
}