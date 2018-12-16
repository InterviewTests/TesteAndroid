package com.avanade.santander.contato.domain.usecase;

import android.support.annotation.NonNull;

import com.avanade.santander.UseCase;
import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.data.contato.CellsDataSource;
import com.avanade.santander.data.local.repository.CellsRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class GetCells {

    private final CellsRepository mCellsRepository;

    public GetCells(@NonNull CellsRepository cellsRepository) {
        mCellsRepository = checkNotNull(cellsRepository, "cellsRepository cannot be null!");
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        mCellsRepository.getCells(new CellsDataSource.LoadCellsCallback() {
            @Override
            public void onCellsLoaded(List<Cell> cells) {

                int activeCells = 0;
                int completedCells = 0;

                // We calculate number of active and completed cells
                for (Cell cell : cells) {
                    if (cell.isCompleted()) {
                        completedCells += 1;
                    } else {
                        activeCells += 1;
                    }
                }

                ResponseValue responseValue = new ResponseValue(new Statistics(completedCells, activeCells));
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onDataNotAvailable() {
                getUseCaseCallback().onError();
            }
        });
    }

    public static class RequestValues implements UseCase.RequestValues {
    }

    public static class ResponseValue implements UseCase.ResponseValue {

        private final Statistics mStatistics;

        public ResponseValue(@NonNull Statistics statistics) {
            mStatistics = checkNotNull(statistics, "statistics cannot be null!");
        }

        public Statistics getStatistics() {
            return mStatistics;
        }
    }
}

}
