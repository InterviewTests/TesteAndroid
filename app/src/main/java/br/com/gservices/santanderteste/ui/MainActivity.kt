package br.com.gservices.santanderteste.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.gservices.santanderteste.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val FIRST_FRAGMENT_TO_OPEN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpViewPager()

        setUpTabLayout()
    }

    private fun setUpViewPager() {
        val adapterViewPager = MainPageAdapter(supportFragmentManager, applicationContext)
        pager.adapter = adapterViewPager
        pager.currentItem = FIRST_FRAGMENT_TO_OPEN
    }

    private fun setUpTabLayout() {
        tab.setupWithViewPager(pager)
    }
}