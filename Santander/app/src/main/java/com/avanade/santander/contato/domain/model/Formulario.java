package com.avanade.santander.contato.domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.avanade.santander.contato.domain.model.Cell;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "formulario")
public class Formulario {

    public static final String TABLE_NAME = "formulario";

    @PrimaryKey
    private int id;

    @SerializedName("cells")
    @Expose
    private List<Cell> cells = null;

    public Formulario(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getFormulario() {
        return cells;
    }
}
