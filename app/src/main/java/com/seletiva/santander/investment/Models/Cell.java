package com.seletiva.santander.investment.Models;

public class Cell {
    private int id;
    private int type;
    private String message;

    private String typefield;
    private boolean hidden;
    private float topSpacing;

    private Integer show;
    private boolean require;

    public void setType(CellType type) {
        this.id = 0;
        this.show = -1;
        this.type = type.getType();
    }

    public int getType() {
        return type;
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
}
