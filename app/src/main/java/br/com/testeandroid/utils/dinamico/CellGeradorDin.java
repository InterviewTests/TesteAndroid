package br.com.testeandroid.utils.dinamico;

import android.view.View;

import br.com.testeandroid.model.Cells;

public class CellGeradorDin {
    private CellDinamico cellDinamico;

    public CellGeradorDin(CellDinamico cellDinamico) {
        this.cellDinamico = cellDinamico;
    }

    public View createCell(Cells cell) {
        View view = cellDinamico.crearCell(cell);
        return view;
    }
}
