package com.avanade.santander.contato.presentation;

import android.widget.TextView;

import com.avanade.santander.BasePresenter;
import com.avanade.santander.BaseView;
import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.contato.domain.model.Formulario;

import java.util.List;

/**
 * Camada de Apresentação - (Contrato)
 * Interface de controle entre IView e IPresenter
 */
public interface ContatoContract {

    interface IView extends BaseView<IPresenter> {

        void desenhaTela(Formulario formulario);

        void setLoadingIndicator(boolean active);

        void showLoadingFormularioError();

        boolean isActive();

        void showMessage(String message);

        void toastMessage(String message);

        void mostrarTelaEnviada();
    }

    interface IPresenter extends BasePresenter {

        void getFormulario();

        void refreshFormulario();

        List<String> validaContato(String nome, String email, String telefone);

        void enviarContato(String nome, String email, String telefone);


    }
}
