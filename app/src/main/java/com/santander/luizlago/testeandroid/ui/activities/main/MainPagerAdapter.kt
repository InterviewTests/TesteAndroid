package com.santander.luizlago.testeandroid.ui.activities.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.santander.luizlago.testeandroid.R
import com.santander.luizlago.testeandroid.ui.fragments.contact.ContactFragment
import com.santander.luizlago.testeandroid.ui.fragments.investment.InvestmentFragment

class MainPagerAdapter(val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    companion object {
        const val TAB_VIEW_INVESTMENT: Int = 0
        const val TAB_VIEW_CONTACT: Int = 1
    }

    private val TAB_VIEW_COUNT = 2

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            TAB_VIEW_INVESTMENT -> InvestmentFragment.newInstance()
            TAB_VIEW_CONTACT -> ContactFragment.newInstance()
            else -> null
        }
    }

    override fun getCount(): Int {
        return TAB_VIEW_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            TAB_VIEW_CONTACT -> context.getString(R.string.tab_contact_title)
            TAB_VIEW_INVESTMENT -> context.getString(R.string.tab_investment_title)
            else -> super.getPageTitle(position)
        }
    }

}