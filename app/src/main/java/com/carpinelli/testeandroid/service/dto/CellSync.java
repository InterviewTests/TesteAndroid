package com.carpinelli.testeandroid.service.dto;

import com.carpinelli.testeandroid.model.Cell;

import java.util.List;

public class CellSync {

    private List<Cell> cells;

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
