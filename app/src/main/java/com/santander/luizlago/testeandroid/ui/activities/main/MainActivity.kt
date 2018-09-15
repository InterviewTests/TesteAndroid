package com.santander.luizlago.testeandroid.ui.activities.main

import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.santander.luizlago.testeandroid.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureStatusBar()
        configureTabs()
    }

    private fun configureStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun configureTabs() {
        val adapter = MainPagerAdapter(this, this.supportFragmentManager)
        this.viewPagerMain.adapter = adapter
        this.tabMain.setupWithViewPager(this.viewPagerMain)
        this.viewPagerMain.currentItem = MainPagerAdapter.TAB_VIEW_CONTACT
    }

}
