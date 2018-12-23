package com.seletiva.santander.investment.models;

import com.seletiva.santander.investment.ui.form.domain.model.Cell;
import com.seletiva.santander.investment.ui.form.domain.model.CellHolder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CellHolderTest {
    private CellHolder cellHolder;
    private Cell cell;
    private Cell secondCell;

    @Before
    public void setUp() {
        cell = new Cell();
        secondCell = new Cell();
        cellHolder = new CellHolder();

        cell.setId(0);
        secondCell.setId(1);
        cellHolder.addCell(cell);
    }

    @Test
    public void testCellHolder() {
        assertNotNull(cellHolder);
        assertNotNull(cellHolder.getCells());
    }

    @Test
    public void testAddCellToCellHolder() {
        assertEquals(cellHolder.getNumberOfCells(), 1);
    }

    @Test
    public void testRemoveCellFromCellHolder() {
        cellHolder.removeCell(cell);
        assertEquals(cellHolder.getNumberOfCells(), 0);
    }

    @Test
    public void testGetCellById() {
        secondCell.setShow(cell.getId());
        cellHolder.addCell(secondCell);

        Cell recoveredCell = cellHolder.getCellById(secondCell.getShow());
        Cell nonRecoveredCell = cellHolder.getCellById(-1);

        assertEquals(recoveredCell, cell);
        assertNull(nonRecoveredCell);
    }
}