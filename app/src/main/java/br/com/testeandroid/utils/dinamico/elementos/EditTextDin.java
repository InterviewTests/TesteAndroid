package br.com.testeandroid.utils.dinamico.elementos;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import br.com.testeandroid.model.Cells;
import br.com.testeandroid.utils.MaskFone;
import br.com.testeandroid.utils.dinamico.CellDinamico;

import static br.com.testeandroid.utils.Constants.INPUT_TYPE_TEL_NUMBER;

public class EditTextDin extends BaseDin implements CellDinamico {

    public EditTextDin(Context context) {
        super(context);
    }

    @Override
    public View crearCell(Cells cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        EditText viewEdit = new EditText(getContext());
        viewEdit.setHint(cell.getMessage());
        viewEdit.setId(cell.getId());

        if (cell.getTypeField().equals(INPUT_TYPE_TEL_NUMBER)) {
            viewEdit.addTextChangedListener(new MaskFone());
            viewEdit.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
        }

        if (cell.getTypeField() != null) {
            viewEdit.setInputType(getInputType(cell));
        }

        if (cell.getHidden()) {
            viewEdit.setVisibility(View.GONE);
        }


        viewEdit.setLayoutParams(params);

        return viewEdit;
    }
}