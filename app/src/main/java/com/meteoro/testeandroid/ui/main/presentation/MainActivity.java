package com.meteoro.testeandroid.ui.main.presentation;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.ui.main.presentation.adapter.MainFragmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Views
    ////////////////////////////////////////////////////////////////////////////////////////////////
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.fragments_pager)
    ViewPager fragmentsPager;

    private MainFragmentAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        initializeToolbar();
        initializeViews();
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void initializeToolbar() {
        setSupportActionBar(toolbar);
    }

    private void initializeViews() {
        mainAdapter = new MainFragmentAdapter(getSupportFragmentManager(), this);
        fragmentsPager.setAdapter(mainAdapter);
        tabLayout.setupWithViewPager(fragmentsPager);
    }
}
