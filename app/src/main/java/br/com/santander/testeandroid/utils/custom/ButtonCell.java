package br.com.santander.testeandroid.utils.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.contact.model.CellResponse;
import br.com.santander.testeandroid.utils.CellContract;

public class ButtonCell extends BaseCell implements CellContract {

    private View.OnClickListener listener;

    public ButtonCell(Context context, @Nullable View.OnClickListener l) {
        super(context);
        setListener(l);
    }

    @Override
    public View createCell(CellResponse cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        Button view = new Button(getContext());
        view.setText(cell.getMessage());
        view.setId(cell.getId());
        view.setBackgroundResource(R.drawable.button_selector);

        view.setLayoutParams(params);

        view.setOnClickListener(listener);

        return view;
    }

    public void setListener(OnClickListener listener) {
        this.listener = listener;
    }

    public OnClickListener getListener() {
        return listener;
    }
}
