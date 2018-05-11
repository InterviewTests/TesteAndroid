package com.santander.android.ui

import android.content.Context
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.santander.android.R
import java.util.*
import android.support.v4.view.ViewCompat.setScaleY
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.santander.android.util.TabSelectedWrapper


class MainActivity : AppCompatActivity() {

    private lateinit var mPagerAdapter: PagerAdapter

    private lateinit var mViewPager: ViewPager
    private lateinit var mTabLayout: TabLayout

    private lateinit var mFundFragment: FundFragment
    private lateinit var mContactFragment: ContactFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadViews()
        loadFragments()
        configViewPager()
        configTabLayout()
    }

    private fun loadViews() {
        mViewPager = findViewById(R.id.activity_main_view_pager)
        mTabLayout = findViewById(R.id.activity_main_tab_layout)
    }

    private fun loadFragments() {
        mFundFragment = FundFragment.getInstance()
        mContactFragment = ContactFragment.getInstance()
    }

    private fun configViewPager() {
        mPagerAdapter = PagerAdapter(supportFragmentManager)
        mPagerAdapter.addFragment(mFundFragment)
        mPagerAdapter.addFragment(mContactFragment)
        mViewPager.adapter = mPagerAdapter
    }

    private fun configTabLayout() {
        mTabLayout.setupWithViewPager(mViewPager)
        val firstTab = ((mTabLayout.getChildAt(0) as LinearLayout).getChildAt(0) as LinearLayout).getChildAt(1) as TextView
        val secondTab = ((mTabLayout.getChildAt(0) as LinearLayout).getChildAt(1) as LinearLayout).getChildAt(1) as TextView
        firstTab.scaleY = -1f
        secondTab.scaleY = -1f
        mTabLayout.addOnTabSelectedListener(object: TabSelectedWrapper() {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {

                    val thisPosition = it.position
                    val thisTab = ((mTabLayout.getChildAt(0) as LinearLayout).getChildAt(thisPosition) as LinearLayout)

                    val lastPosition = if (thisPosition == 0) 1 else 0
                    val lastTab = ((mTabLayout.getChildAt(0) as LinearLayout).getChildAt(lastPosition) as LinearLayout)

//                    val thisParams = thisTab.layoutParams
//                    thisParams.height = Math.floor(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 54f, resources.displayMetrics).toDouble()).toInt()
//                    thisTab.layoutParams = thisParams
//
//                    val lastParams = lastTab.layoutParams
//                    lastParams.height = Math.floor(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics).toDouble()).toInt()
//                    lastTab.layoutParams = lastParams

                }
            }
        })
    }

    private inner class PagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

        private val mFragmentList: MutableList<Fragment> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        fun addFragment(fragment: Fragment) {
            mFragmentList.add(fragment)
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            if (position == 0) return getString(R.string.investment)
            if (position == 1) return getString(R.string.contact)
            else return getString(R.string.error)
        }

    }

}