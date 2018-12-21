package com.avanade.santander.data;

import android.support.annotation.NonNull;

import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.contato.domain.model.Formulario;

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
     * Primeiro tentat buscar Formulário na RestAPI e gravar sua atualiazação,
     * se não conseguir, tenta LOCAL (SQLite) na última atualização.
     * <p>
     * Note: {@link LoadCellsCallback#onDataNotAvailable()} is fired if all data sources fail
     */
    @Override
    public void getFormulario(@NonNull final LoadCellsCallback callback) {
        checkNotNull(callback);

        mCellsRemoteDataSource.getFormulario(new LoadCellsCallback() {

            @Override
            public void onCellsLoaded(Formulario formulario) {

                refreshCells(formulario.getCells());    // Executa Refresh Local

                callback.onCellsLoaded(formulario);
            }


            // Se online não estiver disponível, busca a última informação local gravada
            @Override
            public void onDataNotAvailable() {

                mCellsLocalDataSource.getFormulario(new LoadCellsCallback() {

                    @Override
                    public void onCellsLoaded(Formulario formulario) {
                        callback.onCellsLoaded(formulario);
                    }

                    @Override
                    public void onDataNotAvailable() {
                        callback.onDataNotAvailable();
                    }
                });
            }
        });
    }

    public void refreshCells(List<Cell> cells) {
        // Atualiza tabela LOCAL
        mCellsLocalDataSource.refreshCells(cells);
    }
}
