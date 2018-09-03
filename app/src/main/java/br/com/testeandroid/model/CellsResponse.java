package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CellsResponse {
    @SerializedName("cells")
    private ArrayList<Cells> cells;

    public ArrayList<Cells> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cells> cells) {
        this.cells = cells;
    }
}
