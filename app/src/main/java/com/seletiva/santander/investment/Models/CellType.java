package com.seletiva.santander.investment.Models;

public enum CellType {
    field(1),
    text(2),
    image(3),
    checkbox(4),
    send(5);

    private int type;

    CellType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
