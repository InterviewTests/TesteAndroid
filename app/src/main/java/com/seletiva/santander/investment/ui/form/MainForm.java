package com.seletiva.santander.investment.ui.form;

import android.content.Context;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.BaseView;
import com.seletiva.santander.investment.ui.view.FormComponentView;

/**
 * Contrato de View/Presenter para o fragment de formulário.
 * <p><b>Presenter: </b>{@link MainFormPresenter}</p>
 * <p><b>View: </b>{@link MainFormFragment}</p>
 */
public interface MainForm {
    interface Presenter extends BasePresenter {
        /**
         * Solicita carregamento das cells via servico
         */
        void loadCells();

        /**
         * Solicita finalizacao do presenter (ex: remover EventBus)
         */
        void stop();

        /**
         * Solicitar a limpeza de todos os campos do formulário
         */
        void clearForm();
    }

    interface View extends BaseView {
        Context getContext();

        /**
         * Adicionar uma nova view ao container utilizado para o
         * fomulário
         * @param view View a ser adicionada
         */
        void addFormComponent(FormComponentView view);

        /**
         * Indica que todos os campos do formulário são válidos
         */
        void formValidated();
    }
}
