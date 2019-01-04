package lucasonofre.santandertest.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import lucasonofre.santandertest.fragment.ContatoFragment
import lucasonofre.santandertest.fragment.InvestimentoFragment


class PagerAdapter(fragmentManager: FragmentManager, var numberTabs: Int) : FragmentStatePagerAdapter(fragmentManager) {

    /**
     * Recebe a posição das tabs e configura os fragments de acordo
     * */
    override fun getItem(position: Int): Fragment? {

        return when (position) {

            0 -> {  InvestimentoFragment()  }

            1 -> { ContatoFragment()  }

            else -> null
        }
    }

    override fun getCount(): Int {
        return numberTabs
    }
}