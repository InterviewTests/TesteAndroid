package com.avanade.santander.fundos.presentation;

import com.avanade.santander.BasePresenter;
import com.avanade.santander.BaseView;
import com.avanade.santander.fundos.domain.model.Fundos;

public interface FundosContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void desenhaTela(Fundos fundos);
        boolean isActive();
        void showLoadingFundosError();
    }

    interface Presenter extends BasePresenter {
        void refreshFundos();
        void baixarInfo(String stringURL);
        void investir();
    }
}
