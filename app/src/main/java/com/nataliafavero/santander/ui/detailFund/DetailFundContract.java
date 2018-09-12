package com.nataliafavero.santander.ui.detailFund;

import com.nataliafavero.santander.ui.base.BasePresenter;
import com.nataliafavero.santander.ui.base.BaseView;

/**
 * Created by nataliafavero on 11/09/18.
 */

public interface DetailFundContract {

    interface View extends BaseView<Presenter> {
        void showFund();
    }

    interface Presenter extends BasePresenter {
        void getFund();
    }
}
