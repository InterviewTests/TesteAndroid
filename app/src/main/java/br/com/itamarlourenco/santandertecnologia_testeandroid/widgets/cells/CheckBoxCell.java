package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomCheckBox;

public class CheckBoxCell extends BaseCell {

    public CheckBoxCell(Context context, Cell cell, ViewGroup viewGroup) {
        super(context, cell, viewGroup);
    }

    @Override
    public int idLayout() {
        return R.layout.layout_widget_cell_checkbox;
    }

    @Override
    protected void handleWithView(View view) {
        CustomCheckBox customCheckBox = view.findViewById(R.id.checkbox);
        customCheckBox.setText(getCell().getMessage());
    }
}
