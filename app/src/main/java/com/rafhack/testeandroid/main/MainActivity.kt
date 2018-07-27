package com.rafhack.testeandroid.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.rafhack.testeandroid.R
import com.rafhack.testeandroid.form.FormFragment
import com.rafhack.testeandroid.investment.InvestmentFragment


class MainActivity : AppCompatActivity() {

    private lateinit var vpgFragments: ViewPager
    private lateinit var tblTabs: TabLayout
    private lateinit var tvwTitleText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupActionBar()
        setupView()
    }

    @SuppressLint("InflateParams")
    private fun setupActionBar() {
        val viewActionBar = layoutInflater.inflate(R.layout.custom_actionbar, null, false)
        val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER)
        supportActionBar?.elevation = 0f
        supportActionBar?.setCustomView(viewActionBar, params)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupView() {
        vpgFragments = findViewById(R.id.activity_main_vpg_fragements)
        tblTabs = findViewById(R.id.activity_main_tbl_tabs)
        tvwTitleText = findViewById(R.id.custom_actionbar_title)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(InvestmentFragment(), getString(R.string.investment))
        adapter.addFragment(FormFragment(), getString(R.string.contact))
        vpgFragments.adapter = adapter

        vpgFragments.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                tvwTitleText.text = adapter.getPageTitle(position)
            }
        })

        tblTabs.setupWithViewPager(vpgFragments)

        val tabListed = tblTabs.getChildAt(0) as LinearLayout
        for (position in 0 until tabListed.childCount) {
            val item = tabListed.getChildAt(position) as LinearLayout
            item.rotationX = 180f
        }

        vpgFragments.currentItem = 1
    }


}
