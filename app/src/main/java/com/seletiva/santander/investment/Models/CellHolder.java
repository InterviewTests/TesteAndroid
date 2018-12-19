package com.seletiva.santander.investment.Models;

import java.util.ArrayList;
import java.util.List;

public class CellHolder {
    private List<Cell> cells;

    public CellHolder() {
        cells = new ArrayList<>();
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }

    public List<Cell> getCells() {
        return cells;
    }

    public int getNumberOfCells() {
        return cells.size();
    }

    public void removeCell(Cell cell) {
        cells.remove(cell);
    }

    public Cell getCellById(Integer show) {
        for(Cell cell:cells) {
            if (cell.getId() == show) {
                return cell;
            }
        }

        return null;
    }
}
