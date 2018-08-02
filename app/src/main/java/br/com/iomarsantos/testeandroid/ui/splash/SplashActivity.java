package br.com.iomarsantos.testeandroid.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.ui.base.BaseActivity;
import br.com.iomarsantos.testeandroid.ui.fundo.FundoActivity;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashBasePresenter<SplashView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);

        mPresenter.onAttach(SplashActivity.this);

    }

    @Override
    public void openFundoActivity() {
        Intent intent = FundoActivity.getStartIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

}
