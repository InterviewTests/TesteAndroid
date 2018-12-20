package com.seletiva.santander.investment.UI.Activities;

import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.seletiva.santander.investment.Models.Cell;
import com.seletiva.santander.investment.Models.CellHolder;
import com.seletiva.santander.investment.Models.CellType;
import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;
import com.seletiva.santander.investment.UI.View.FormBuilderException;
import com.seletiva.santander.investment.UI.View.FormComponentView;
import com.seletiva.santander.investment.Utils.FileUtils;

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
            FormBuilder formBuilder = new FormBuilder(this);
            FormComponentView textualView = new FormComponentView(this);
            Gson gson = new Gson();
            String fileContent = FileUtils.loadData("cells.json", this);
            CellHolder mainHolder = gson.fromJson(fileContent, CellHolder.class);

            for(Cell cell:mainHolder.getCells()) {
                FormComponentView componentView = new FormComponentView(this);
                componentView.inflateWithCellModel(cell);

                formBuilder.addView(componentView);
            }
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