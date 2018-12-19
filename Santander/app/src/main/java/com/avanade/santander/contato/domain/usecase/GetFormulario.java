package com.avanade.santander.contato.domain.usecase;

import android.support.annotation.NonNull;

import com.avanade.santander.UseCase;
import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.contato.domain.model.Formulario;
import com.avanade.santander.data.CellsDataSource;
import com.avanade.santander.data.CellsRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class GetFormulario extends UseCase<GetFormulario.RequestValues, GetFormulario.ResponseValue> {

    private final CellsRepository mCellsRepository;

    public GetFormulario(@NonNull CellsRepository cellsRepository) {
        mCellsRepository = checkNotNull(cellsRepository, "cellsRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mCellsRepository
                .getFormulario(
                        new CellsDataSource.LoadCellsCallback() {

                            @Override
                            public void onCellsLoaded(Formulario formulario) {
                                ResponseValue responseValue = new ResponseValue(formulario);
                                getUseCaseCallback().onSuccess(responseValue);
                            }

                            @Override
                            public void onDataNotAvailable() {
                                getUseCaseCallback().onError();
                            }
                        }
                )
        ;
    }

    public static class RequestValues implements UseCase.RequestValues {
        /**
         * Também aqui não há necessidade de enviar dados para receber o formulário
         * será útil num Http POST ou PUT
         */
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final Formulario mFormulario;

        public ResponseValue(@NonNull Formulario cells) {
            mFormulario = checkNotNull(cells, "statistics cannot be null!");
        }

        public Formulario getFormulario() {
            return mFormulario;
        }
    }
}
