package br.com.santander.desafio.main

import br.com.enzoteles.quickhelp.fragment.HelpManagerFragment
import br.com.santander.desafio.content.ContentFragment
import dagger.Module
import dagger.Provides

/**
 * Created by Enzo Teles on 22,June,2018
 * Barueri - SP
 * email: enzo.carvalho.teles@gmail.com
 * Software Developer Senior Mobile
 */
@Module
class MainModule(val activity: MainActivity){

    @Provides
    fun provideContentFragment(): ContentFragment{
        return  ContentFragment()
    }

    @Provides
    fun provideHelpManagerFragment(): HelpManagerFragment {
        return  HelpManagerFragment(activity)
    }
}