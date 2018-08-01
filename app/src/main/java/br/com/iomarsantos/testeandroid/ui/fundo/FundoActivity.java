package br.com.iomarsantos.testeandroid.ui.fundo;

import android.os.Bundle;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.ui.base.BaseActivity;

public class FundoActivity extends BaseActivity implements FundoView {

    @Inject
    FundoBasePresenter<FundoView> mPresenter;

    @Inject
    FundoPagerAdapter fundoPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fundo);

        getActivityComponent().inject(this);

        mPresenter.onAttach(FundoActivity.this);

    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

}
