package com.seletiva.santander.investment.ui.form;

import android.content.Context;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.BaseView;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;
import com.seletiva.santander.investment.ui.view.FormComponentView;

public interface MainForm {
    interface Presenter extends BasePresenter {
        void stop();
        void loadCells();
    }

    interface View extends BaseView {
        Context getContext();

        void addFormComponent(FormComponentView view);

        void formValidated();
    }
}
