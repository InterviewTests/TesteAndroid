package com.study.vipoliveira.investapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.study.vipoliveira.investapp.R
import com.study.vipoliveira.investapp.ui.contact.ContactFormFragment
import com.study.vipoliveira.investapp.ui.investment.InvestFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),  HasSupportFragmentInjector{

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return androidInjector
    }

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Fragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.addFragment(InvestFragment(), getString(R.string.investment_title))
        viewPagerAdapter.addFragment(ContactFormFragment(), getString(R.string.contact_title))
        viewPager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(viewPager)

    }
}
