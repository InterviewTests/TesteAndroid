package br.com.santander.santanderinvestimento.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.santander.santanderinvestimento.R
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
        val adapterViewPager = DashboardPageAdapter(supportFragmentManager, applicationContext)

        vpDashboard.adapter = adapterViewPager

        vpDashboard.currentItem = FIRST_FRAGMENT_TO_OPEN
    }

    private fun setUpTabLayout() {
        tabDashboard.setupWithViewPager(vpDashboard)
    }
}