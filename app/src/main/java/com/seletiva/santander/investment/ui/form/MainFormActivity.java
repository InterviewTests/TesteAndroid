package com.seletiva.santander.investment.ui.form;

import android.widget.LinearLayout;
import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.ui.BaseActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainFormActivity extends BaseActivity {
    @ViewById
    LinearLayout formContainer;

    @AfterViews
    public void init() {}
}