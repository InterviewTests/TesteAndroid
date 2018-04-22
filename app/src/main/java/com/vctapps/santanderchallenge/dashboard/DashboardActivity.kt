package com.vctapps.santanderchallenge.dashboard

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vctapps.santanderchallenge.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val FIRST_FRAGMENT_TO_OPEN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dashboard)

        setUpViewPager()

        setUpTabLayout()
    }

    private fun setUpViewPager() {
        val adapterViewPager = DashboardPageAdapter(supportFragmentManager)

        viewPagerDashboard.adapter = adapterViewPager

        viewPagerDashboard.currentItem = FIRST_FRAGMENT_TO_OPEN
    }

    private fun setUpTabLayout() {
        tabLayoutDashboard.setupWithViewPager(viewPagerDashboard)
    }
}
