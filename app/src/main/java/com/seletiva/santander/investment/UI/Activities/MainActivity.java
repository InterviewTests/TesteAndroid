package com.seletiva.santander.investment.UI.Activities;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.UI.View.Form;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements Form {
    @ViewById
    LinearLayout formContainer;

    @AfterViews
    public void init() {}

    @Override
    public LinearLayout getFormContainer() {
        return formContainer;
    }

    @Override
    public LayoutInflater getInflater() {
        return getLayoutInflater();
    }
}