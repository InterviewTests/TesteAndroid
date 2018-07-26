package com.rafhack.testeandroid.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.form.FormFragment
import com.rafhack.testeandroid.investiment.InvestimentFragment


class MainActivity : AppCompatActivity() {

    private lateinit var vpgFragments: ViewPager
    private lateinit var tblTabs: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView() {
        vpgFragments = findViewById(R.id.activity_main_vpg_fragements)
        tblTabs = findViewById(R.id.activity_main_tbl_tabs)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(InvestimentFragment(), getString(R.string.investiment))
        adapter.addFragment(FormFragment(), getString(R.string.contact))
        vpgFragments.adapter = adapter


        tblTabs.setupWithViewPager(vpgFragments)

        val tabListed = tblTabs.getChildAt(0) as LinearLayout
        for (position in 0 until tabListed.childCount) {
            val item = tabListed.getChildAt(position) as LinearLayout
            item.rotationX = 180f
        }
    }


}
