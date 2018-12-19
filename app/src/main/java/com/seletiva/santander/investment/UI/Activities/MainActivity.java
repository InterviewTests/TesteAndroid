package com.seletiva.santander.investment.UI.Activities;

import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;
import com.seletiva.santander.investment.UI.View.FormBuilderException;
import com.seletiva.santander.investment.UI.View.FormFieldListener;
import com.seletiva.santander.investment.UI.View.FormFieldWatcher;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements Form, FormFieldListener {
    @ViewById
    LinearLayout formContainer;

    @AfterViews
    public void init() {
        poc();
    }

    public void poc() {
        try {
            FormBuilder formBuilder = new FormBuilder(this);

            formBuilder.addViewById(R.layout.form_component_checkbox)
                    .addViewById(R.layout.form_component_field)
                    .addViewById(R.layout.form_component_text);

            EditText mailForm = formBuilder
                    .recoverViewById(R.layout.form_component_field)
                    .findViewById(R.id.textualData);

            mailForm.addTextChangedListener(new FormFieldWatcher(mailForm, this));
        } catch (FormBuilderException e) {
            e.printStackTrace();
        }

    }

    @Override
    public LinearLayout getFormContainer() {
        return formContainer;
    }

    @Override
    public LayoutInflater getInflater() {
        return getLayoutInflater();
    }

    @Override
    public void updateEditTextColor(EditText targetEditText, int color) {
        targetEditText.getBackground().setColorFilter(getResources().getColor(color),
                PorterDuff.Mode.SRC_ATOP);
    }
}