package com.vctapps.santanderchallenge.dashboard

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.vctapps.santanderchallenge.R
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val adapterViewPager = DashboardPageAdapter(supportFragmentManager)

        viewPagerDashboard.adapter = adapterViewPager

        tabLayoutDashboard.setupWithViewPager(viewPagerDashboard)

    }
}
