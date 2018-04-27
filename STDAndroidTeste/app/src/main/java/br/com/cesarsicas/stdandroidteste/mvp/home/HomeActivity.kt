package br.com.cesarsicas.stdandroidteste.mvp.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import br.com.cesarsicas.stdandroidteste.R
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.view.PagerAdapter
import android.view.View
import br.com.cesarsicas.stdandroidteste.AppComponent
import br.com.cesarsicas.stdandroidteste.MainApplication
import br.com.cesarsicas.stdandroidteste.mvp.home.contact.ContactFragment
import br.com.cesarsicas.stdandroidteste.mvp.home.investment.InvestmentFragment
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private val NUM_PAGES = 2

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private var mPager: ViewPager? = null

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private var mPagerAdapter: PagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById<View>(R.id.pager) as ViewPager?
        mPagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager!!.adapter = mPagerAdapter


        contactButton.setOnClickListener {
            mPager?.currentItem = 1
        }

        investmentButton.setOnClickListener {
            mPager?.currentItem = 0
        }
    }

    override fun onBackPressed() {
        if (mPager!!.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            mPager!!.currentItem = mPager!!.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
           return  when (position) {
                0 -> InvestmentFragment()
                1 -> ContactFragment()
                else -> InvestmentFragment()
            }
        }

        override fun getCount(): Int {
            return NUM_PAGES
        }
    }


}
