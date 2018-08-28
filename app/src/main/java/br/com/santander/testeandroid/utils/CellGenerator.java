package br.com.santander.testeandroid.utils;

import android.content.Context;
import android.view.View;

import br.com.santander.testeandroid.contact.model.CellResponse;

public class CellGenerator {

    private CellContract cellContract;

    public CellGenerator(CellContract cellContract) {
        this.cellContract = cellContract;
    }

    public View createCell(CellResponse cell) {
        View view = cellContract.createCell(cell);
        return view;
    }

}
