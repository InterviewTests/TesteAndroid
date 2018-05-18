package com.anabhomasi.androidapp

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class FundPagerAdapter(fragmentManager: FragmentManager, private val funds: ArrayList<Fund>) :
        FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return FundFragment.newInstance(funds[position])
    }

    override fun getCount(): Int {
        return funds.size
    }
}