package com.example.alessandrofsouza.santanderapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager());
        adapter.add(new ContactFragment(), getString(R.string.contato));
        adapter.add(new InvestmentFragment(), getString(R.string.investimento));

        ViewPager viewPager = findViewById(R.id.viewPage);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.pageTab);
        tabLayout.setupWithViewPager(viewPager);
    }
}
