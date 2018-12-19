package com.avanade.santander.fundos.presentation;

import com.avanade.santander.BasePresenter;
import com.avanade.santander.BaseView;
import com.avanade.santander.fundos.domain.model.Fundos;

/**
 * Camada de Apresentação - (Contrato)
 *
 * Interface de controle entre IView e IPresenter
 */
public interface FundosContract {

    interface IView extends BaseView<IPresenter> {
        void setLoadingIndicator(boolean active);
        void desenhaTela(Fundos fundos);
        boolean isActive();
        void showLoadingFundosError();
        void abrirLinkBaixarInfo(String stringURL);
        void iniciaActivityContato();
        void share();
    }

    interface IPresenter extends BasePresenter {
        void refreshFundos();
    }
}
