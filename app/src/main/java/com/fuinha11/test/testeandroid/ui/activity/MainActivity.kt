package com.fuinha11.test.testeandroid.ui.activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.fuinha11.test.testeandroid.R
import com.fuinha11.test.testeandroid.contract.ContactFragContracts
import com.fuinha11.test.testeandroid.contract.InvestmentFragContracts
import com.fuinha11.test.testeandroid.contract.MainScreenContracts
import com.fuinha11.test.testeandroid.data.model.Investment
import com.fuinha11.test.testeandroid.presenter.MainPresenter
import com.fuinha11.test.testeandroid.ui.fragment.ContactFragment
import com.fuinha11.test.testeandroid.ui.fragment.ContactFragment_
import com.fuinha11.test.testeandroid.ui.fragment.InvestmentFragment
import com.fuinha11.test.testeandroid.ui.fragment.InvestmentFragment_
import com.fuinha11.test.testeandroid.ui.view.CellHolder
import kotlinx.android.synthetic.main.activity_main.*
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity

@EActivity(R.layout.activity_main)
open class MainActivity :
        BaseActivity(),
        MainScreenContracts.View,
        InvestmentFragContracts.Parent,
        ContactFragContracts.Parent
{

    @Bean
    lateinit var presenter: MainPresenter

    lateinit var investFrag : InvestmentFragment
    lateinit var contactFrag : ContactFragment

    override fun initComponents() {
        investFrag = InvestmentFragment_.builder().build()
        contactFrag = ContactFragment_.builder().build()

        val adapter = object : FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(index: Int): Fragment {
                when (index) {
                    0 -> {
                        return investFrag
                    }
                    else -> {
                        return contactFrag
                    }
                }
            }

            override fun getPageTitle(index: Int): CharSequence? {
                when (index) {
                    0 -> {
                        return "Investimento"
                    }
                    else -> {
                        return "Contato"
                    }
                }
            }
            override fun getCount(): Int = 2
        }

        main_pager.adapter = adapter
        main_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {}

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

            override fun onPageSelected(index: Int) {
                when (index) {
                    0 -> {
                        presenter.getInvestment()
                    }
                    else -> {
                        presenter.getFields()
                    }
                }
            }
        })

        main_tabs.setupWithViewPager(main_pager)
        showInvestmentFragment()
    }

    override fun showContactFragment() {
        main_pager.currentItem = 1
        presenter.getFields()
    }

    override fun populateContactFragment(cells: List<CellHolder>) {
        contactFrag.populateContactFragment(cells)
    }


    override fun showCellErrors() = contactFrag.showCellErrors()

    override fun showContactView() = contactFrag.showContactView()

    override fun showThankYouView() = contactFrag.showThankYouView()

    override fun showInvestmentFragment() {
        main_pager.currentItem = 0
        presenter.getInvestment()
    }

    override fun populateInvestmentFragment(investment: Investment) = investFrag.populateInvestmentFragment(investment)

    override fun onInvestBtnClick() {}

    override fun onSendBtnClick() = presenter.sendBtnClick()

}
