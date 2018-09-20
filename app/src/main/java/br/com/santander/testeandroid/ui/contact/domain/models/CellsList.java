package br.com.santander.testeandroid.ui.contact.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CellsList {
    @SerializedName("cells")
    private List<Cell> cellList;

    public List<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(List<Cell> cellList) {
        this.cellList = cellList;
    }
}
