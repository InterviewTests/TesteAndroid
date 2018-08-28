package com.alex.testeandroid.presentation.funds;

import com.alex.testeandroid.data.entities.funds.Funds;
import com.alex.testeandroid.presentation.BaseView;

/**
 * Created by Alex on 27/08/18.
 */
public interface FundsView extends BaseView {

    void showProgress(boolean show);

    void showMessageErrorRequest();

    void loadData(Funds funds);
}
