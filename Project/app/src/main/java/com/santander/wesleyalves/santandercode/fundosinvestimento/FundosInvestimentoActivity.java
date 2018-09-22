package com.santander.wesleyalves.santandercode.fundosinvestimento;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.santander.wesleyalves.santandercode.R;
import com.santander.wesleyalves.santandercode._utils.ActivityUtils;

public class FundosInvestimentoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundos_investimento);

        FundosInvestimentoFragment fundosInvestimentoFragment =
                (FundosInvestimentoFragment) getSupportFragmentManager().findFragmentById(R.id.fundos_investimento_fragment);
        if (fundosInvestimentoFragment == null) {
            // Create the fragment
            fundosInvestimentoFragment = FundosInvestimentoFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), fundosInvestimentoFragment, R.id.fundos_investimento_fragment);
        }
    }
}
