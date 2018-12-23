package com.seletiva.santander.investment.ui.form.domain.model;

public enum CellTypeField {
    text(1),
    telNumber(2),
    email(3);

    private int type;

    CellTypeField(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
