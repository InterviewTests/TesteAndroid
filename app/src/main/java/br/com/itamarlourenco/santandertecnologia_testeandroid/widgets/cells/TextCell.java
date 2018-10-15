package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomTextView;

public class TextCell extends BaseCell {

    public TextCell(Context context, Cell cell, ViewGroup viewGroup, BaseCell.OnClickListener onClickListener) {
        super(context, cell, viewGroup, onClickListener);
    }

    @Override
    public int idLayout() {
        return R.layout.layout_widget_cell_text;
    }

    @Override
    protected void handleWithView(View view) {
        CustomTextView customTextView = view.findViewById(R.id.textView);
        customTextView.setText(getCell().getMessage());
    }

}
