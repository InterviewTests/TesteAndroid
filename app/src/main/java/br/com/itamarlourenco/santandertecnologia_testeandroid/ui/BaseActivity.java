package br.com.itamarlourenco.santandertecnologia_testeandroid.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        handleFragments();
    }

    private void handleFragments() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_content, getFragment());
        ft.commit();
    }

    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    protected abstract BaseFragment getFragment();


}
