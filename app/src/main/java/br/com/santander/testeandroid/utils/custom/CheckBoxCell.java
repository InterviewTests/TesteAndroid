package br.com.santander.testeandroid.utils.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.contact.model.CellResponse;
import br.com.santander.testeandroid.utils.CellContract;

public class CheckBoxCell extends BaseCell implements CellContract {
    @Nullable
    private CompoundButton.OnCheckedChangeListener listener;

    public CheckBoxCell(Context context, @Nullable CompoundButton.OnCheckedChangeListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public View createCell(CellResponse cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        CheckBox view = new CheckBox(getContext());
        view.setText(cell.getMessage());
        view.setId(Integer.parseInt(String.valueOf(cell.getId())));
        setStyle(view, R.style.AppThemeTextView);

        view.setOnCheckedChangeListener(listener);
        view.setLayoutParams(params);
        return view;
    }

}
