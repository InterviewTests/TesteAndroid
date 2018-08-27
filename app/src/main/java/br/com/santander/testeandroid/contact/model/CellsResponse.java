package br.com.santander.testeandroid.contact.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CellsResponse {

    @SerializedName("cells")
    private List<CellResponse> cells;

    public List<CellResponse> getCells() {
        return cells;
    }

    public void setCells(List<CellResponse> cells) {
        this.cells = cells;
    }
}
