package com.seletiva.santander.investment.UI.Activities;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.seletiva.santander.investment.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById
    LinearLayout formContainer;

    @AfterViews
    public void init() {
        pocViewBuilder();
    }

    private void pocViewBuilder() {
        if (formContainer != null) {
            View childA = getLayoutInflater().inflate(R.layout.form_component_checkbox, null);
            View childB = getLayoutInflater().inflate(R.layout.form_component_text, null);
            formContainer.addView(childB);
            formContainer.addView(childA);
        }
    }
}