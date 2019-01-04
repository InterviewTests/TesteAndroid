package lucasonofre.santandertest.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import lucasonofre.santandertest.R

class MainActivity : AppCompatActivity() {

    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tab_layout)

        tabLayout?.let {
            setTabLayout(it)
            setTabLayoutAdapter()
        }

    }
    /**
     * Nomeação das tabs
     * */
    private fun setTabLayout(tabLayout:TabLayout) {
        tabLayout?.addTab(tabLayout.newTab().setText("Investimento"))
        tabLayout?.addTab(tabLayout.newTab().setText("Contato"))
        tabLayout?.tabGravity = TabLayout.GRAVITY_FILL
    }
    /**
     * Configurado o view pager e adicionando o adapter
     * */
    private fun setTabLayoutAdapter() {
        var tabAdapter = lucasonofre.santandertest.adapter.PagerAdapter(supportFragmentManager, tabLayout?.tabCount!!)
        viewPager?.adapter = tabAdapter

        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }



}
