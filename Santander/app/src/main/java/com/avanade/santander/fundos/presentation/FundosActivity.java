package com.avanade.santander.fundos.presentation;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.avanade.santander.R;
import com.avanade.santander.util.ActivityUtils;
import com.avanade.santander.util.EspressoIdlingResource;

public class FundosActivity extends AppCompatActivity {

    FundosPresenter fundosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundos_layout);


        FundosFragment fundosFragment = (FundosFragment) getSupportFragmentManager().findFragmentById(R.id.scrollView);

        if (fundosFragment == null) {
            // Create the fragment
            fundosFragment = FundosFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fundosFragment, R.id.scrollView);
        }

        // Create the presenter
        fundosPresenter = new FundosPresenter(
                Injection.provideUseCaseHandler(),
                fundosFragment,
                Injection.provideGetFundos(getApplicationContext())
        );

    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
