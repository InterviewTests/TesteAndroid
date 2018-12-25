package com.seletiva.santander.investment.ui.form;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.datasources.cells.CellsDatasource;
import com.seletiva.santander.investment.ui.BaseFragment;
import com.seletiva.santander.investment.ui.tabs.domain.TabClickEvent;
import com.seletiva.santander.investment.ui.view.FormComponentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@EFragment(R.layout.fragment_contact)
public class MainFormFragment extends BaseFragment implements MainForm.View {
    @ViewById
    LinearLayout formContainer;

    private MainFormPresenter presenter;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getContext() != null) {
            if (isVisibleToUser) {
                EventBus.getDefault().post(new TabClickEvent(getString(R.string.tab_contact_title)));
            }
        }
    }

    @AfterViews
    public void init() {
        CellsDatasource controller = new CellsDatasource();
        presenter = new MainFormPresenter(this, controller, Schedulers.newThread(),
                AndroidSchedulers.mainThread());
        presenter.start();
        presenter.loadCells();
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void addFormComponent(FormComponentView view) {
        formContainer.addView(view);
    }

    @Override
    public void formValidated() {
        Toast.makeText(getActivity(), "Validated!!!", Toast.LENGTH_LONG).show();
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