package com.seletiva.santander.investment.Models;

public class Cell {
    private int id;
    private int type;
    private CellType cellType;
    private String message;

    private String typefield;
    private boolean hidden;
    private float topSpacing;

    private Integer show;
    private boolean require;

    public Cell() {
        this.id = 0;
        this.show = -1;
    }

    public void setType(CellType type) {
        this.cellType = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setShow(int showCell) {
        show = showCell;
    }

    public Integer getShow() {
        return show;
    }

    public int getId() {
        return id;
    }

    public CellType getType() {
        return type == 0 ? CellType.values()[type] : CellType.values()[type - 1];
    }
}
