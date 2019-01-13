package br.com.rafael.santanderteste.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import br.com.rafael.santanderteste.presentation.fragment.FundFragment

class FragmentHelper {

    companion object {
        /**
         * Atualiza um Fragment que ja esta adicionado em um layout view por outro
         * Verifica se o fragment esta nulo para criar ou apenas exibir
         * @param supportFragmentManager FragmentManager
         * @param layoutReference O id do layout que servira de container para o Fragment
         * @param fragment Fragment que sera adicionado/atualizado no container de layout
         */
        fun replace_framgment(supportFragmentManager: FragmentManager, layoutReference: Int, fragment: Fragment, tag : String) {
            if(tag.equals("invest")) {
                if(supportFragmentManager.findFragmentByTag("invest") != null) {
                    supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("invest")).commit();
                } else {
                    supportFragmentManager.beginTransaction().add(layoutReference, fragment, "invest").commit();
                }
                if(supportFragmentManager.findFragmentByTag("contact") != null) {
                    supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("contact")).commit();
                }
            } else {
                if(supportFragmentManager.findFragmentByTag("contact") != null) {
                    supportFragmentManager.beginTransaction().show(supportFragmentManager.findFragmentByTag("contact")).commit();
                } else {
                    supportFragmentManager.beginTransaction().add(layoutReference, fragment, "contact").commit();
                }
                if(supportFragmentManager.findFragmentByTag("invest") != null) {
                    supportFragmentManager.beginTransaction().hide(supportFragmentManager.findFragmentByTag("invest")).commit();
                }
            }

        }
    }
}