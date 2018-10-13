package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewGroup;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;

public class FieldCell extends BaseCell {

    public FieldCell(Context context, Cell cell, ViewGroup viewGroup) {
        super(context, cell, viewGroup);
    }

    @Override
    public int idLayout() {
        return R.layout.layout_widget_cell_field;
    }

    @Override
    protected void handleWithView(View view) {
        //CustomEditText customEditText = view.findViewById(R.id.textView);

        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        textInputLayout.setHint(getCell().getMessage());
    }
}
