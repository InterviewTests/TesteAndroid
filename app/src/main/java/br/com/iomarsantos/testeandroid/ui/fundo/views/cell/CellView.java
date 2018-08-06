package br.com.iomarsantos.testeandroid.ui.fundo.views.cell;

import android.view.View;

import br.com.iomarsantos.testeandroid.entity.Cell;

public interface CellView {
    View getView();
    void configuraView(Cell cell);
}
