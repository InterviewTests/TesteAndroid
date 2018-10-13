package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewGroup;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomTextView;

public class TitleCell extends BaseCell {

    public TitleCell(Context context, Cell cell, ViewGroup viewGroup) {
        super(context, cell, viewGroup);
    }

    @Override
    public int idLayout() {
        return R.layout.layout_widget_cell_title;
    }

    @Override
    protected void handleWithView(View view) {
        CustomTextView customTextView = view.findViewById(R.id.textView);
        customTextView.setText(getCell().getMessage());
    }
}
