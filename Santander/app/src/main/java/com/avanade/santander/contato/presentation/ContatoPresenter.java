package com.avanade.santander.contato.presentation;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.avanade.santander.UseCase;
import com.avanade.santander.UseCaseHandler;
import com.avanade.santander.contato.domain.model.Formulario;
import com.avanade.santander.contato.domain.usecase.GetFormulario;
import com.avanade.santander.util.MailUtil;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ContatoPresenter implements ContatoContract.IPresenter {

    private final ContatoContract.IView mContatoView;

    private final UseCaseHandler mUseCaseHandler;

    private final GetFormulario mGetFormulario;

    @Nullable
    private static Formulario FORMULARIO = null;


    public ContatoPresenter(@NonNull UseCaseHandler useCaseHandler,
                            @NonNull ContatoContract.IView contatoView,
                            @NonNull GetFormulario getFormulario) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler cannot be null");
        mContatoView = checkNotNull(contatoView, "contatoView cannot be null!");
        mGetFormulario = checkNotNull(getFormulario, "getFormulario cannot be null!");
        mContatoView.setPresenter(this);
    }

    @Override
    public void start() {
        /** Call USE_CASE -> GetFundos() = buscar dados de Fundos, em Json, para exibir na tela */
        refreshFormulario();
    }

    @Override
    public void refreshFormulario() {
        if (FORMULARIO == null)
            getFormulario();
        else
            mContatoView.desenhaTela(FORMULARIO);
    }

    @Override
    public void getFormulario() {

        // Exibe icone de loading até efetuar atualização
        mContatoView.setLoadingIndicator(true);

        /**
         * Request para POST VALUES or Save Data
         */
        GetFormulario.RequestValues requestValue =
                new GetFormulario.RequestValues(/* Não vamos passar nenhum parametro de request aqui */);


        /**
         * Response para retorno obtido da API
         */
        mUseCaseHandler
                .execute(mGetFormulario, requestValue,
                        new UseCase.UseCaseCallback<GetFormulario.ResponseValue>() {
                            @Override
                            public void onSuccess(GetFormulario.ResponseValue response) {
                                // The view may not be able to handle UI updates anymore
                                if (!mContatoView.isActive())
                                    return;

                                FORMULARIO = response.getFormulario();
                                mContatoView.desenhaTela(FORMULARIO);
                                mContatoView.setLoadingIndicator(false);
                            }

                            @Override
                            public void onError() {
                                // The view may not be able to handle UI updates anymore
                                if (!mContatoView.isActive())
                                    return;

                                /* Solicita a view para mostrar mensagem de erro */
                                mContatoView.showLoadingFormularioError();
                            }
                        }
                )
        ;
    }

    @Override
    public List<String> validaContato(String nome, String email, String telefone) {

        List<String> erros = new ArrayList<>();

        if (nome.length() < 3)
            erros.add("Preencha o campo nome correntamente");

        if (telefone.length() < 14)
            erros.add("Preencha o campo telefone correntamente");

        if (!MailUtil.isValid(email))
            erros.add("E-mail obrigatório: preencha-o correntamente");

        return erros;
    }

    @Override
    public void enviarContato(String nome, String email, String telefone) {

        List<String> erros = validaContato(nome, email, telefone);

        if (erros.size() <= 0) {
            // TODO - Verificar com Product Owner o que fazer com a informação do usuário
            mContatoView.mostrarTelaEnviada();

        } else {
            String mensagem = "";

            for (String s : erros)
                mensagem += "\n -> " + s + "\n";

            mContatoView.toastMessage(mensagem);
        }
    }


}
