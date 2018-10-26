package br.com.andreyneto.testesantander.ui

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import br.com.andreyneto.testesantander.R
import br.com.andreyneto.testesantander.ui.contact.ContactFragment
import br.com.andreyneto.testesantander.ui.contact.ContactPresenter
import br.com.andreyneto.testesantander.ui.invest.InvestFragment
import br.com.andreyneto.testesantander.ui.invest.InvestPresenter

class FragmentAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                val fragment = InvestFragment.newInstance()
                InvestPresenter(fragment)
                return fragment
            }
            else -> {
                val fragment = ContactFragment.newInstance()
                ContactPresenter(fragment)
                return fragment
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return mContext.resources.getString(R.string.invest_title)
            else -> return mContext.resources.getString(R.string.contact_title)
        }
    }
}
