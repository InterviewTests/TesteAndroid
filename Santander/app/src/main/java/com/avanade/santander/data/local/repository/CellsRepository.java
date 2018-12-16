package com.avanade.santander.data.local.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.avanade.santander.contato.domain.model.Cell;
import com.avanade.santander.data.contato.CellsDataSource;
import com.avanade.santander.data.contato.CellsLocalDataSource;
import com.avanade.santander.data.contato.CellsRemoteDataSource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;


public class CellsRepository implements CellsDataSource {

    private static CellsRepository INSTANCE = null;

    private final CellsRemoteDataSource mCellsRemoteDataSource;

    private final CellsLocalDataSource mCellsLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Cell> mCachedCells;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

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
     * Used to force {@link #getInstance(CellsDataSource, CellsDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets Cells from cache, local data source (SQLite) or remote data source, whichever is
     * available first.
     * <p>
     * Note: {@link LoadCellsCallback#onDataNotAvailable()} is fired if all data sources fail to
     * get the data.
     */
    @Override
    public void getCells(@NonNull final LoadCellsCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedCells != null && !mCacheIsDirty) {
            callback.onCellsLoaded(new ArrayList<>(mCachedCells.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getCellsFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mCellsLocalDataSource.getCells(new LoadCellsCallback() {
                @Override
                public void onCellsLoaded(List<Cell> cells) {
                    refreshCache(cells);
                    callback.onCellsLoaded(new ArrayList<>(mCachedCells.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getCellsFromRemoteDataSource(callback);
                }
            });
        }
    }

    @Override
    public void saveCell(@NonNull Cell cell) {
        checkNotNull(cell);
        mCellsRemoteDataSource.saveCell(cell);
        mCellsLocalDataSource.saveCell(cell);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCells == null) {
            mCachedCells = new LinkedHashMap<>();
        }
        mCachedCells.put(cell.getId(), cell);
    }

    @Override
    public void completeCell(@NonNull Cell cell) {
        checkNotNull(cell);
        mCellsRemoteDataSource.completeCell(cell);
        mCellsLocalDataSource.completeCell(cell);

        Cell completedCell = new Cell(Cell.getTitle(), Cell.getDescription(), Cell.getId(), true);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCells == null) {
            mCachedCells = new LinkedHashMap<>();
        }
        mCachedCells.put(Cell.getId(), completedCell);
    }

    @Override
    public void completeCell(@NonNull String CellId) {
        checkNotNull(CellId);
        completeCell(getCellWithId(CellId));
    }

    @Override
    public void activateCell(@NonNull Cell Cell) {
        checkNotNull(Cell);
        mCellsRemoteDataSource.activateCell(Cell);
        mCellsLocalDataSource.activateCell(Cell);

        Cell activeCell = new Cell(Cell.getTitle(), Cell.getDescription(), Cell.getId());

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCells == null) {
            mCachedCells = new LinkedHashMap<>();
        }
        mCachedCells.put(Cell.getId(), activeCell);
    }

    @Override
    public void activateCell(@NonNull String CellId) {
        checkNotNull(CellId);
        activateCell(getCellWithId(CellId));
    }

    @Override
    public void clearCompletedCells() {
        mCellsRemoteDataSource.clearCompletedCells();
        mCellsLocalDataSource.clearCompletedCells();

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCells == null) {
            mCachedCells = new LinkedHashMap<>();
        }
        Iterator<Map.Entry<String, Cell>> it = mCachedCells.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cell> entry = it.next();
            if (entry.getValue().isCompleted()) {
                it.remove();
            }
        }
    }

    /**
     * Gets Cells from local data source (sqlite) unless the table is new or empty. In that case it
     * uses the network data source. This is done to simplify the sample.
     * <p>
     * Note: {@link GetCellCallback#onDataNotAvailable()} is fired if both data sources fail to
     * get the data.
     */
    @Override
    public void getCell(@NonNull final String CellId, @NonNull final GetCellCallback callback) {
        checkNotNull(CellId);
        checkNotNull(callback);

        Cell cachedCell = getCellWithId(CellId);

        // Respond immediately with cache if available
        if (cachedCell != null) {
            callback.onCellLoaded(cachedCell);
            return;
        }

        // Load from server/persisted if needed.

        // Is the Cell in the local data source? If not, query the network.
        mCellsLocalDataSource.getCell(CellId, new GetCellCallback() {
            @Override
            public void onCellLoaded(Cell Cell) {
                // Do in memory cache update to keep the app UI up to date
                if (mCachedCells == null) {
                    mCachedCells = new LinkedHashMap<>();
                }
                mCachedCells.put(Cell.getId(), Cell);
                callback.onCellLoaded(Cell);
            }

            @Override
            public void onDataNotAvailable() {
                mCellsRemoteDataSource.getCell(CellId, new GetCellCallback() {
                    @Override
                    public void onCellLoaded(Cell Cell) {
                        // Do in memory cache update to keep the app UI up to date
                        if (mCachedCells == null) {
                            mCachedCells = new LinkedHashMap<>();
                        }
                        mCachedCells.put(Cell.getId(), Cell);
                        callback.onCellLoaded(Cell);
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
    public void refreshCells() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAllCells() {
        mCellsRemoteDataSource.deleteAllCells();
        mCellsLocalDataSource.deleteAllCells();

        if (mCachedCells == null) {
            mCachedCells = new LinkedHashMap<>();
        }
        mCachedCells.clear();
    }

    @Override
    public void deleteCell(@NonNull String CellId) {
        mCellsRemoteDataSource.deleteCell(checkNotNull(CellId));
        mCellsLocalDataSource.deleteCell(checkNotNull(CellId));

        mCachedCells.remove(CellId);
    }

    private void getCellsFromRemoteDataSource(@NonNull final LoadCellsCallback callback) {
        mCellsRemoteDataSource.getCells(new LoadCellsCallback() {
            @Override
            public void onCellsLoaded(List<Cell> Cells) {
                refreshCache(Cells);
                refreshLocalDataSource(Cells);
                callback.onCellsLoaded(new ArrayList<>(mCachedCells.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<Cell> Cells) {
        if (mCachedCells == null) {
            mCachedCells = new LinkedHashMap<>();
        }
        mCachedCells.clear();
        for (Cell Cell : Cells) {
            mCachedCells.put(Cell.getId(), Cell);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Cell> Cells) {
        mCellsLocalDataSource.deleteAllCells();
        for (Cell Cell : Cells) {
            mCellsLocalDataSource.saveCell(Cell);
        }
    }

    @Nullable
    private Cell getCellWithId(@NonNull String id) {
        checkNotNull(id);
        if (mCachedCells == null || mCachedCells.isEmpty()) {
            return null;
        } else {
            return mCachedCells.get(id);
        }
    }
}