package com.carpinelli.testeandroid.ui.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.carpinelli.testeandroid.R;
import com.carpinelli.testeandroid.ui.form.FormFragment;
import com.carpinelli.testeandroid.ui.invest.InvestFragment;

public class MainActivity extends AppCompatActivity{

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new PageViewAdapter(getSupportFragmentManager()));

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.invest)));
        tabLayout.addTab(tabLayout.newTab().setText(getResources().getText(R.string.form)));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

    }


    public class PageViewAdapter extends FragmentPagerAdapter {

        public PageViewAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new InvestFragment();
                case 1:
                    return new FormFragment();
            }
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
