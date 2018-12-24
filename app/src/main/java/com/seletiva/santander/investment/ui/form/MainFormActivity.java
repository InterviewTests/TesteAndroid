package com.seletiva.santander.investment.ui.form;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.controllers.cells.CellsController;
import com.seletiva.santander.investment.ui.BaseActivity;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.ui.view.FormComponentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@EActivity(R.layout.activity_main)
public class MainFormActivity extends BaseActivity implements MainForm.View {
    @ViewById
    LinearLayout formContainer;

    private MainFormPresenter presenter;

    @AfterViews
    public void init() {
        CellsController controller = new CellsController();
        presenter = new MainFormPresenter(this, controller, Schedulers.newThread(),
                AndroidSchedulers.mainThread());
        presenter.start();
        presenter.loadCells();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void addFormComponent(FormComponentView view) {
        formContainer.addView(view);
    }

    @Override
    public void formValidated() {
        Toast.makeText(this, "Validated!!!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showMessage(int resId) {

    }
}