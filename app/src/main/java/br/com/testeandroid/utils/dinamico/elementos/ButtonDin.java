package br.com.testeandroid.utils.dinamico.elementos;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;

import br.com.testeandroid.R;
import br.com.testeandroid.model.Cells;
import br.com.testeandroid.utils.dinamico.CellDinamico;

public class ButtonDin extends BaseDin implements CellDinamico {
    private View.OnClickListener listener;

    public ButtonDin(Context context, @Nullable View.OnClickListener l) {
        super(context);
        setListener(l);
    }

    @Override
    public View crearCell(Cells cell) {
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout
                .LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        Button view = new Button(getContext());
        view.setText(cell.getMessage());
        view.setId(cell.getId());
        view.setBackgroundResource(R.drawable.button_backgroud);
        view.setTextAppearance(getContext(), R.style.ButtonConfig);
        view.setAllCaps(false);
        view.setLayoutParams(params);

        view.setOnClickListener(listener);

        return view;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public View.OnClickListener getListener() {
        return listener;
    }
}
