package com.avanade.santander.data;

import android.support.annotation.NonNull;

import com.avanade.santander.contato.domain.model.Cell;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Concrete implementation to load cells from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
public class CellsRepository implements CellsDataSource {

    private static CellsRepository INSTANCE = null;

    private final CellsDataSource mCellsRemoteDataSource;

    private final CellsDataSource mCellsLocalDataSource;

    // Prevent direct instantiation.
    private CellsRepository(@NonNull CellsDataSource cellsRemoteDataSource,
                            @NonNull CellsDataSource cellsLocalDataSource) {
        mCellsRemoteDataSource = checkNotNull(cellsRemoteDataSource);
        mCellsLocalDataSource = checkNotNull(cellsLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param cellsRemoteDataSource the backend data source
     * @param cellsLocalDataSource  the device storage data source
     * @return the {@link CellsRepository} instance
     */
    public static CellsRepository getInstance(CellsDataSource cellsRemoteDataSource,
                                              CellsDataSource cellsLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new CellsRepository(cellsRemoteDataSource, cellsLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Try Get Cells Remote, caso contrário, tenta LOCAL (SQLite) if exists
     * <p>
     * Note: {@link LoadCellsCallback#onDataNotAvailable()} is fired if all data sources fail
     */
    @Override
    public void getCells(@NonNull final LoadCellsCallback callback) {
        checkNotNull(callback);

        mCellsRemoteDataSource.getCells(new LoadCellsCallback() {
            @Override
            public void onCellsLoaded(List<Cell> cells) {
                // Executa Refresh
                refreshCells(cells);
                callback.onCellsLoaded(cells);
            }

            @Override
            public void onDataNotAvailable() {
                mCellsLocalDataSource.getCells(new LoadCellsCallback() {
                    @Override
                    public void onCellsLoaded(List<Cell> cells) {
                        callback.onCellsLoaded(cells);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    @Override
    public void refreshCells(List<Cell> cells) {
        // Atualiza tabela LOCAL
        mCellsLocalDataSource.refreshCells(cells);
    }
}
