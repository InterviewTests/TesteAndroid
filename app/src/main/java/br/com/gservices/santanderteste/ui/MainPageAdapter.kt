package br.com.gservices.santanderteste.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import br.com.gservices.santanderteste.R
import br.com.gservices.santanderteste.pages.contacts.presenter.ContactFragment
import br.com.gservices.santanderteste.pages.investments.presenter.InvestmentsFragment

class MainPageAdapter(fragmentManager: FragmentManager, var context: Context) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> InvestmentsFragment.newInstance()
            1 -> ContactFragment.newInstance()
            else ->  ContactFragment.newInstance()
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