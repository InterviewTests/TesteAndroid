package com.anabhomasi.androidapp.views.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.anabhomasi.androidapp.views.fragments.DynamicFormFragment
import com.anabhomasi.androidapp.views.fragments.FundFragment
import com.anabhomasi.androidapp.views.fragments.SuccessFormFragment

class PageAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {

    // Returns total number of pages
    override fun getCount(): Int {
        return NUM_ITEMS
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment? {
        return when (position) {
            FUND_PAGE -> FundFragment.newInstance("Investimento", FUND_PAGE)
            FORM_PAGE -> DynamicFormFragment.newInstance()
            SUCCESS_FORM_PAGE -> SuccessFormFragment.newInstance("Contato", SUCCESS_FORM_PAGE)
            else -> null
        }
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            FUND_PAGE -> "Investimento"
            FORM_PAGE, SUCCESS_FORM_PAGE -> "Contato"
            else -> ""
        }
    }

    companion object {
        private const val NUM_ITEMS = 3

        const val FUND_PAGE = 0
        const val FORM_PAGE = 1
        const val SUCCESS_FORM_PAGE = 2
    }
}