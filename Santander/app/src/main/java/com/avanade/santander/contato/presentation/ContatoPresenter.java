package com.avanade.santander.contato.presentation;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.avanade.santander.UseCase;
import com.avanade.santander.UseCaseHandler;
import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.contato.domain.usecase.GetCells;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ContatoPresenter implements ContatoContract.IPresenter {

    private final ContatoContract.IView mContatoView;

    private final UseCaseHandler mUseCaseHandler;

    private final GetCells mGetCells;

    @Nullable
    private static List<Cell> FORMULARIO = null;


    public ContatoPresenter(@NonNull UseCaseHandler useCaseHandler,
                            @NonNull ContatoContract.IView contatoView,
                            @NonNull GetCells getCells) {
        mUseCaseHandler = checkNotNull(useCaseHandler, "useCaseHandler cannot be null");
        mContatoView = checkNotNull(contatoView, "contatoView cannot be null!");
        mGetCells = checkNotNull(getCells, "getCells cannot be null!");
        mContatoView.setPresenter(this);
    }

    @Override
    public void start() {
        /** Call USE_CASE -> GetFundos() = buscar dados de Fundos, em Json, para exibir na tela */
        refreshFormulario();
    }

    @Override
    public void refreshFormulario() {
        AsyncTask.execute(() -> {
            if (FORMULARIO == null)
                getFormulario();
            mContatoView.desenhaTela(FORMULARIO);
            mContatoView.setLoadingIndicator(false);
        });
    }

    @Override
    public void getFormulario() {

        // Exibe icone de loading até efetuar atualização
        mContatoView.setLoadingIndicator(true);

        /**
         * Request para POST VALUES or Save Data
         */
        GetCells.RequestValues requestValue =
                new GetCells.RequestValues(/* Não vamos passar nenhum parametro de request aqui */);


        /**
         * Response para retorno obtido da API
         */
        mUseCaseHandler
                .execute(mGetCells, requestValue,
                        new UseCase.UseCaseCallback<GetCells.ResponseValue>() {
                            @Override
                            public void onSuccess(GetCells.ResponseValue response) {
                                // The view may not be able to handle UI updates anymore
                                if (!mContatoView.isActive())
                                    return;
                                FORMULARIO = response.getCells();
                            }

                            @Override
                            public void onError() {
                                // The view may not be able to handle UI updates anymore
                                if (!mContatoView.isActive())
                                    return;

                                /** Solicita a view para mostrar mensagem de erro */
                                mContatoView.showLoadingFormularioError();
                            }
                        }
                )
        ;
    }

    @Override
    public void enviaFormulario() {
        // TODO - Verificar com Product Owner o que fazer com a informação do usuário
        mContatoView.exibeMensagemEnviada();
    }


}
