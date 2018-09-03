package br.com.testeandroid.utils.dinamico.elementos;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import br.com.testeandroid.model.Cells;
import br.com.testeandroid.utils.dinamico.CellDinamico;

public class TextViewDin extends BaseDin implements CellDinamico {

    public TextViewDin(Context context) {
        super(context);
    }

    @Override
    public View crearCell(Cells cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        TextView view = new TextView(getContext());
        view.setText(cell.getMessage());
        view.setId(cell.getId());

        view.setLayoutParams(params);

        return view;
    }
}
