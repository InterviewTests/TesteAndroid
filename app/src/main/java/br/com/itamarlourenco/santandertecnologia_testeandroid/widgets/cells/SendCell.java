package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomButton;

public class SendCell extends BaseCell {

    public SendCell(Context context, Cell cell, ViewGroup viewGroup) {
        super(context, cell, viewGroup);
    }

    @Override
    public int idLayout() {
        return R.layout.layout_widget_cell_send;
    }

    @Override
    protected void handleWithView(View view) {
        CustomButton button = view.findViewById(R.id.button);
        button.setText(getCell().getMessage());
    }
}
