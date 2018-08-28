package br.com.santander.testeandroid.utils.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;

import br.com.santander.testeandroid.contact.model.CellResponse;
import br.com.santander.testeandroid.utils.CellContract;

import static br.com.santander.testeandroid.utils.Constants.INPUT_TYPE_TEL_NUMBER;

public class EditTextCell extends BaseCell implements CellContract {

    public EditTextCell(Context context) {
        super(context);
    }

    @Override
    public View createCell(CellResponse cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        EditText view = new EditText(getContext());
        view.setHint(cell.getMessage());
        view.setId(cell.getId());

        if (cell.getTypeField().equals(INPUT_TYPE_TEL_NUMBER)) {
            view.addTextChangedListener(new PhoneTextWatcher());
            view.setFilters(new InputFilter[]{new InputFilter.LengthFilter(14)});
        }

        if (cell.getTypeField() != null) {
            view.setInputType(getInputType(cell));
        }

        if (cell.getHidden()) {
            view.setVisibility(View.GONE);
        }


        view.setLayoutParams(params);

        return view;
    }

}
