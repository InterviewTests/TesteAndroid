package com.seletiva.santander.investment.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CellTest {
    private Cell singleCell;

    @org.junit.Before
    public void setUp() throws Exception {
        singleCell = new Cell();
    }

    @Test
    public void testCellNotNull() {
        assertNotNull(singleCell);
    }

    @Test
    public void testCellType() {
        CellType fieldType = CellType.field;
        CellType imageType = CellType.image;

        singleCell.setType(fieldType);
        assertEquals(singleCell.getType(), fieldType.getType());
        assertNotEquals(singleCell.getType(), imageType.getType());
    }
}