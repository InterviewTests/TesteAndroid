package com.vctapps.santanderchallenge.dashboard

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vctapps.santanderchallenge.form.presentation.view.FormFragment

class DashboardPageAdapter(fragmentManager: FragmentManager):
        FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> FormFragment()
            else -> FormFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Investmento"
            1 -> "Contato"
            else -> ""
        }
    }
}