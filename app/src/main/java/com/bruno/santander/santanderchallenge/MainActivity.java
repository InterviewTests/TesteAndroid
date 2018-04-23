package com.bruno.santander.santanderchallenge;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bruno.santander.santanderchallenge.contato.ContatoFragment;
import com.bruno.santander.santanderchallenge.investimento.InvestimentoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();

        viewPagerAdapter.addFragment(InvestimentoFragment.newInstance(), "Investimento");
        viewPagerAdapter.addFragment(ContatoFragment.newInstance(), "Contato");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setup() {
        setUnbinder(ButterKnife.bind(this));

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    }
}
