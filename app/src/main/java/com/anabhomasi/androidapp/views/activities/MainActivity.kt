package com.anabhomasi.androidapp.views.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.anabhomasi.androidapp.R
import com.anabhomasi.androidapp.views.adapters.PageAdapter
import com.anabhomasi.androidapp.views.fragments.DynamicFormFragment
import com.anabhomasi.androidapp.views.fragments.FormFragment
import com.anabhomasi.androidapp.views.fragments.FundFragment
import com.anabhomasi.androidapp.views.fragments.SuccessFormFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
        DynamicFormFragment.OnFragmentInteractionListener,
        FundFragment.OnFragmentInteractionListener,
        SuccessFormFragment.OnFragmentInteractionListener{

    override fun onFragmentInteraction(page: Int) {

        myViewPager.currentItem = page
    }

    private var adapterViewPager: FragmentPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterViewPager = PageAdapter(supportFragmentManager)
        myViewPager.adapter = adapterViewPager

        btnContato.setOnClickListener {
            myViewPager.currentItem = 1
        }

        btnInvestimento.setOnClickListener {
            myViewPager.currentItem = 0
        }

        toolbar_title.text = myViewPager.adapter?.getPageTitle(myViewPager.currentItem)
        adjustButtonColorStates(myViewPager.currentItem)

        // Attach the page change listener inside the activity
        myViewPager.addOnPageChangeListener(object : OnPageChangeListener {

            // This method will be invoked when a new page becomes selected.
            override fun onPageSelected(position: Int) {
                toolbar_title.text = myViewPager.adapter?.getPageTitle(position)
                adjustButtonColorStates(position)
            }

            // This method will be invoked when the current page is scrolled
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            override fun onPageScrollStateChanged(state: Int) {
                // Code goes here
            }
        })

    }
    private fun adjustButtonColorStates(position: Int) {
        when(position){
            0 -> {
                btnInvestimento.background = selectedState()
                btnContato.background = unselectedState()
            }
            1 -> {
                btnInvestimento.background = unselectedState()
                btnContato.background = selectedState()
            }
        }
    }

    private fun selectedState() : Drawable?{
        return ContextCompat.getDrawable(this, R.color.colorPrimaryDark)
    }

    private fun unselectedState() : Drawable?{
        return ContextCompat.getDrawable(this, R.drawable.tab_unselected_color_state)
    }

}