package com.carpinelli.testeandroid.model;

public enum TypeField {
    TEXT(1), TELNUMBER(2), EMAIL(3);

    private final int id;

    TypeField(int id) {
        this.id = id;
    }

    public int getValue() {
        return id;
    }
}
