package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomCheckBox;

public class CheckBoxCell extends BaseCell {

    private CustomCheckBox customCheckBox;

    public CheckBoxCell(Context context, Cell cell, ViewGroup viewGroup, BaseCell.OnClickListener onClickListener) {
        super(context, cell, viewGroup, onClickListener);
    }

    @Override
    public int idLayout() {
        return R.layout.layout_widget_cell_checkbox;
    }

    @Override
    protected void handleWithView(View view) {
        customCheckBox = view.findViewById(R.id.checkbox);
        customCheckBox.setText(getCell().getMessage());

        customCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                getCell().setValue(isChecked ? "Sim" : "NAO");
            }
        });

        customCheckBox.setButtonDrawable(R.drawable.checkbox);
    }
}
