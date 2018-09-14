package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by nataliafavero on 10/09/18.
 */

public class CellResponse {
    @Expose
    private List<Cell> cells;

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
