package br.com.santander.santanderinvestimento.dashboard

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.santander.santanderinvestimento.R
import br.com.santander.santanderinvestimento.investiment.presentation.InvestmentFragment

class DashboardPageAdapter(fragmentManager: FragmentManager, var context: Context) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> InvestmentFragment.newInstance()
            1 -> Fragment()
            else ->  InvestmentFragment.newInstance()
        }

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> context.getString(R.string.investiment)
            1 -> context.getString(R.string.contact)
            else -> ""
        }
    }
}
