package com.seletiva.santander.investment.ui.form;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.datasources.cells.CellsDatasource;
import com.seletiva.santander.investment.ui.BaseFragment;
import com.seletiva.santander.investment.ui.tabs.domain.TabClickEvent;
import com.seletiva.santander.investment.ui.view.FormComponentView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.EventBus;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Fragmento de contato/formulario
 */
@EFragment(R.layout.fragment_contact)
public class MainFormFragment extends BaseFragment implements MainForm.View {
    @ViewById
    View progress;

    @ViewById
    LinearLayout formContainer;

    @ViewById
    View messageSent;

    private MainFormPresenter presenter;

    @AfterViews
    public void init() {
        configureUI();
        configureData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getContext() != null) {
            if (isVisibleToUser) {
                EventBus.getDefault().post(new TabClickEvent(getString(R.string.tab_contact_title)));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.stop();
    }

    private void configureUI() {
        progress.setVisibility(View.VISIBLE);
        messageSent.setVisibility(View.GONE);
        formContainer.setVisibility(View.GONE);
    }

    private void configureData() {
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
        messageSent.setVisibility(View.VISIBLE);
        formContainer.setVisibility(View.GONE);
    }

    @Click
    void sendNewMessage() {
        presenter.clearForm();
        messageSent.setVisibility(View.GONE);
        formContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgressDialog() {
        progress.setVisibility(View.VISIBLE);
        formContainer.setVisibility(View.GONE);
    }

    @Override
    public void hideProgressDialog() {
        progress.setVisibility(View.GONE);
        formContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(int resId) {
        DialogInterface.OnClickListener tryAgain = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                presenter.loadCells();
            }
        };

        showTryAgainMessage(resId, tryAgain);
    }
}