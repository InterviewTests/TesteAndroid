package com.example.savio.testeandroid;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.savio.testeandroid.adapter.ViewPagerPagerAdapter;
import com.example.savio.testeandroid.view.FormFragment;
import com.example.savio.testeandroid.view.FundFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerPagerAdapter viewPagerPagerAdapter;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.menu_tabs);
        viewPagerPagerAdapter = new ViewPagerPagerAdapter(getSupportFragmentManager());

        //create the tabs
        viewPagerPagerAdapter.addFragment(new FundFragment(), "Investimento");
        viewPagerPagerAdapter.addFragment(new FormFragment(), "Contato");

        //setup the view pager
        viewPager.setAdapter(viewPagerPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
