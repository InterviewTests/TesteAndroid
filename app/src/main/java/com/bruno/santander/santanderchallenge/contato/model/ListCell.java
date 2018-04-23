package com.bruno.santander.santanderchallenge.contato.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ListCell {

    @JsonProperty("cells")
    private ArrayList<Cell> listCells;

    public ArrayList<Cell> getListCells() {
        return listCells;
    }

    public void setListCells(ArrayList<Cell> listCells) {
        this.listCells = listCells;
    }

}
