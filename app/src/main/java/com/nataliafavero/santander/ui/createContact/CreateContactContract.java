package com.nataliafavero.santander.ui.createContact;

import com.nataliafavero.santander.data.model.Cell;
import com.nataliafavero.santander.ui.base.BasePresenter;
import com.nataliafavero.santander.ui.base.BaseView;

import java.util.List;

/**
 * Created by nataliafavero on 11/09/18.
 */

public interface CreateContactContract {

    interface View extends BaseView<Presenter> {
        void showCells(List<Cell> cellList);
    }

    interface Presenter extends BasePresenter {
        void getCells();
    }
}
