package br.com.ibm.santander.wallacebaldenebre.model;

public class Cells {
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
