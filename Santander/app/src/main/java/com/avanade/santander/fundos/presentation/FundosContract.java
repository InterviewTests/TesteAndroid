package com.avanade.santander.fundos.presentation;

import android.content.Context;

import com.avanade.santander.BasePresenter;
import com.avanade.santander.BaseView;
import com.avanade.santander.fundos.domain.model.Fundos;

/**
 * Camada de Apresentação - (Contrato)
 *
 * Interface de controle entre View e Presenta
 */
public interface FundosContract {

    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void desenhaTela(Fundos fundos);
        boolean isActive();
        void showLoadingFundosError();
        void abrirUrlDownloadInfo(String stringURL);
    }

    interface Presenter extends BasePresenter {
        void refreshFundos();

        void investir();
    }
}
