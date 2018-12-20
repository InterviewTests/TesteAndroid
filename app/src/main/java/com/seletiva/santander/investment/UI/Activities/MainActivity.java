package com.seletiva.santander.investment.UI.Activities;

import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.seletiva.santander.investment.Models.Cell;
import com.seletiva.santander.investment.Models.CellType;
import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;
import com.seletiva.santander.investment.UI.View.FormBuilderException;
import com.seletiva.santander.investment.UI.View.FormComponentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements Form {
    @ViewById
    LinearLayout formContainer;

    @AfterViews
    public void init() {
        poc();
    }

    public void poc() {
        try {
            Cell cellModel = new Cell();
            cellModel.setType(CellType.field);
            FormBuilder formBuilder = new FormBuilder(this);
            FormComponentView textualView = new FormComponentView(this);

            textualView.inflateWithCellModel(cellModel);
            formBuilder.addView(textualView);
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
}