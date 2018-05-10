package com.santander.android.util

import android.support.design.widget.TabLayout

abstract class TabSelectedWrapper : TabLayout.OnTabSelectedListener {

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // Optional Implementation.
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // Optional Implementation.
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        // Optional Implementation.
    }

}