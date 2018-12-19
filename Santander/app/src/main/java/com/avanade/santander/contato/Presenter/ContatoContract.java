package com.avanade.santander.contato.Presenter;

import android.widget.TextView;

import com.avanade.santander.BasePresenter;
import com.avanade.santander.BaseView;
import com.avanade.santander.contato.domain.model.Cell;

import java.util.List;

/**
 * Camada de Apresentação - (Contrato)
 * Interface de controle entre IView e IPresenter
 */
public interface ContatoContract {

    interface IView extends BaseView<IPresenter> {

        void desenhaTela(List<Cell> formulario);

        void setLoadingIndicator(boolean active);

        void showLoadingFormularioError();

        void setCampoValidado(IView validada);

        void validaEmail(TextView textView);

        void validaTelefone(TextView textView);

        void limpaTexto(TextView textView);

        void exibeMensagemEnviada();

        boolean isActive();
    }

    interface IPresenter extends BasePresenter {

        void getFormulario();

        void enviaFormulario();

    }
}
