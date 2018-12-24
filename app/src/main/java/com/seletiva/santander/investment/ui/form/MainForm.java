package com.seletiva.santander.investment.ui.form;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.BaseView;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;

public interface MainForm {
    interface Presenter extends BasePresenter {
        void loadCells();
    }

    interface View extends BaseView {
        void buildCellsUsingHolder(CellHolder holder);
    }
}
