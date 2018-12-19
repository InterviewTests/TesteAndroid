package com.avanade.santander.contato.domain.usecase;

import android.support.annotation.NonNull;

import com.avanade.santander.UseCase;
import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.data.contato.CellsDataSource;
import com.avanade.santander.data.local.repository.CellsRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class GetCells extends UseCase<GetCells.RequestValues, GetCells.ResponseValue> {

    private final CellsRepository mCellsRepository;

    public GetCells(@NonNull CellsRepository cellsRepository) {
        mCellsRepository = checkNotNull(cellsRepository, "tasksRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mCellsRepository
                .getCells(
                        new CellsDataSource.LoadCellsCallback() {

                            @Override
                            public void onCellsLoaded(List<Cell> cells) {
                                ResponseValue responseValue = new ResponseValue(cells);
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

        private final List<Cell> mCells;

        public ResponseValue(@NonNull List<Cell> cells) {
            mCells = checkNotNull(cells, "statistics cannot be null!");
        }

        public List<Cell> getCells() {
            return mCells;
        }
    }
}
