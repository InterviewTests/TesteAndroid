package com.avanade.santander.data.local;


import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.data.CellsDataSource;
import com.avanade.santander.util.AppExecutors;

import java.util.List;


/**
 * Concrete implementation of a data source as a db.
 */
public class CellsLocalDataSource implements CellsDataSource {

    private static volatile CellsLocalDataSource INSTANCE;

    private CellsDao mCellsDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private CellsLocalDataSource(@NonNull AppExecutors appExecutors,
                                 @NonNull CellsDao cellsDao) {
        mAppExecutors = appExecutors;
        mCellsDao = cellsDao;
    }

    public static CellsLocalDataSource getInstance(@NonNull AppExecutors appExecutors,
                                                   @NonNull CellsDao cellsDao) {
        if (INSTANCE == null) {
            synchronized (CellsLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new CellsLocalDataSource(appExecutors, cellsDao);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Note: {@link LoadCellsCallback#onDataNotAvailable()} is fired if the database doesn't exist
     * or the table is empty.
     */
    @Override
    public void getCells(@NonNull final LoadCellsCallback callback) {
        Runnable runnable = () -> {
            final List<Cell> cells = mCellsDao.getAllCells();
            mAppExecutors.mainThread().execute(() -> {
                if (cells.isEmpty()) {
                    // This will be called if the table is new or just empty.
                    callback.onDataNotAvailable();
                } else {
                    callback.onCellsLoaded(cells);
                }
            });
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    public void refreshCells(List<Cell> cells) {
        AsyncTask.execute(() -> {
            mCellsDao.deleteCells();
            mCellsDao.insertAll((Cell[]) cells.toArray(new Cell[0]));
        });
    }

    @VisibleForTesting
    static void clearInstance() {
        INSTANCE = null;
    }
}
