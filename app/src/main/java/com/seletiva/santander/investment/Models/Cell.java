package com.seletiva.santander.investment.Models;

public class Cell {
    private int id;
    private CellType type;
    private String message;

    private int typefield;
    private boolean hidden;
    private float topSpacing;

    private Integer show;
    private boolean require;

    public Cell() {
        this.id = 0;
        this.show = -1;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public int getTypefield() {
        return typefield;
    }

    public void setTypefield(CellTypeField typefield) {
        this.typefield = typefield.getType();
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
        return type;
    }
}
