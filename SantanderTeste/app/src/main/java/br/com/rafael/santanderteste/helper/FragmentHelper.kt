package br.com.rafael.santanderteste.helper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

class FragmentHelper {

    companion object {
        /**
         * Atualiza um Fragment que ja esta adicionado em um layout view por outro
         * @param supportFragmentManager FragmentManager
         * @param layoutReference O id do layout que servira de container para o Fragment
         * @param fragment Fragment que sera adicionado/atualizado no container de layout
         */
        fun replace_framgment(supportFragmentManager: FragmentManager, layoutReference: Int, fragment: Fragment) {
            var fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(layoutReference, fragment)
            fragmentTransaction.commit()
        }
    }
}