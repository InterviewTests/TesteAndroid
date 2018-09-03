package br.com.testeandroid.utils.dinamico.elementos;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.com.testeandroid.R;
import br.com.testeandroid.feature.contato.ContatoFragment;
import br.com.testeandroid.model.Cells;
import br.com.testeandroid.utils.dinamico.CellDinamico;

public class CheckBoxDin extends BaseDin implements CellDinamico {
    @Nullable
    private CompoundButton.OnCheckedChangeListener CheckBoxlistener;

    public CheckBoxDin(Context context, @Nullable CompoundButton.OnCheckedChangeListener CheckBoxlistener) {
        super(context);
        this.CheckBoxlistener = CheckBoxlistener;
    }

    @Override
    public View crearCell(Cells cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        CheckBox view = new CheckBox(getContext());
        view.setText(cell.getMessage());
        view.setId(Integer.parseInt(String.valueOf(cell.getId())));
        view.setTextAppearance(getContext(), R.style.FonteDINPro_light_regularCinzaClaro_16);

        view.setOnCheckedChangeListener(CheckBoxlistener);
        view.setLayoutParams(params);
        return view;
    }
}
