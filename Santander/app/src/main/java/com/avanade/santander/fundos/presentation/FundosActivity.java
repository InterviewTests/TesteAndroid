package com.avanade.santander.fundos.presentation;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.FragmentActivity;

import com.avanade.santander.Injection;
import com.avanade.santander.R;
import com.avanade.santander.util.ActivityUtils;
import com.avanade.santander.util.EspressoIdlingResource;

/**
 * Camada de apresentação - View (Container)
 */
public class FundosActivity extends FragmentActivity {

    FundosPresenter fundosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fundos_activity);


        FundosFragment fundosFragment = (FundosFragment) getSupportFragmentManager().findFragmentById(R.id.container);

        if (fundosFragment == null) {
            // Create the fragment
            fundosFragment = FundosFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fundosFragment, R.id.container);
        }

        // Create the presenter
        fundosPresenter = new FundosPresenter(
                Injection.provideUseCaseHandler(),
                fundosFragment,
                Injection.provideGetFundos()
        );

    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoIdlingResource.getIdlingResource();
    }
}
