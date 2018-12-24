package com.seletiva.santander.investment.ui.form;

import android.widget.LinearLayout;
import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.controllers.cells.CellsController;
import com.seletiva.santander.investment.ui.BaseActivity;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;

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
    public void buildCellsUsingHolder(CellHolder holder) {

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