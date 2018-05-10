package com.carpinelli.testeandroid.model;

import java.io.Serializable;

public class Cells implements Serializable {
    private Cell[] cells;

    public Cells() {
    }

    public Cells(Cell[] cells) {
        this.cells = cells;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }
}