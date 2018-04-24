package com.aline.teste.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aline.teste.MVP.MVP;
import com.aline.teste.MVP.presenter.PresenterFund;
import com.aline.teste.R;

public class Investimento extends AppCompatActivity {

    PresenterFund  presenterFund = new PresenterFund();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investimento);

        presenterFund.callNetworkFund();
    }
}
