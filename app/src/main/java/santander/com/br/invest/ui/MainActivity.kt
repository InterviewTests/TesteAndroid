package santander.com.br.invest.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import santander.com.br.invest.R
import santander.com.br.invest.ui.adapter.ViewPagerAdapter
import santander.com.br.invest.ui.fragments.ContactFragment
import santander.com.br.invest.ui.fragments.InvestmentFragment


class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initViewPager()
  }

  private fun initViewPager() {
    viewPager.offscreenPageLimit = 2
    tabLayout.setupWithViewPager(viewPager)

    val adapter = ViewPagerAdapter(supportFragmentManager)
    adapter.addFragment(InvestmentFragment(), getString(R.string.investment))
    adapter.addFragment(ContactFragment(), getString(R.string.contact))
    viewPager.adapter = adapter
  }
}