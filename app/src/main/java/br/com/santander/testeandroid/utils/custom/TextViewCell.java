package br.com.santander.testeandroid.utils.custom;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.contact.model.CellResponse;
import br.com.santander.testeandroid.utils.CellContract;

public class TextViewCell extends BaseCell implements CellContract {

    public TextViewCell(Context context) {
        super(context);
    }

    @Override
    public View createCell(CellResponse cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        TextView view = new TextView(getContext());
        view.setText(cell.getMessage());
        view.setId(cell.getId());
        setStyle(view, R.style.AppThemeTextView);

        view.setLayoutParams(params);

        return view;
    }

}
