package lucasonofre.santandertest.view

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import lucasonofre.santandertest.R
import android.support.v4.content.ContextCompat.getSystemService



class MainView: View {

    constructor(context:Context?):super(context){

        val inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
        val mv = inflater!!.inflate(R.layout.activity_main, null)
//
//        viewPager = mv.findViewById(R.id.view_pager)
//        tabLayout = mv.findViewById(R.id.tab_layout)
    }
}