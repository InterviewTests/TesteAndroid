package com.avanade.santander.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.avanade.santander.contato.domain.model.Cell;

import java.util.List;

@Dao
public interface CellsDao {

    /**
     * Select all cells from the cells table.
     *
     * @return all cells.
     */
    @Query("SELECT * FROM " + Cell.TABLE_NAME)
    List<Cell> getAllCells();

    /**
     * Insert all cells
     *
     * @param cells the cells to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Cell... cells);

    /**
     * Delete all cells.
     */
    @Query("DELETE FROM Cells")
    void deleteCells();

}
