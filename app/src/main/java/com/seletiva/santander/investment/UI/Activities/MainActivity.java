package com.seletiva.santander.investment.UI.Activities;

import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.seletiva.santander.investment.Models.Cell;
import com.seletiva.santander.investment.Models.CellHolder;
import com.seletiva.santander.investment.Models.CellType;
import com.seletiva.santander.investment.Models.events.SendButtonClickEvent;
import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.UI.View.Form;
import com.seletiva.santander.investment.UI.View.FormBuilder;
import com.seletiva.santander.investment.UI.View.FormBuilderException;
import com.seletiva.santander.investment.UI.View.FormComponentView;
import com.seletiva.santander.investment.Utils.FileUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements Form {
    @ViewById
    LinearLayout formContainer;

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @AfterViews
    public void init() {
        poc();
    }

    public void poc() {
        try {
            FormBuilder formBuilder = new FormBuilder(this);
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

    @Subscribe
    public void onFormClickEvent(Object event) {
        if (event.getClass() == SendButtonClickEvent.class) {
            Toast.makeText(this, "Click", Toast.LENGTH_LONG).show();
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