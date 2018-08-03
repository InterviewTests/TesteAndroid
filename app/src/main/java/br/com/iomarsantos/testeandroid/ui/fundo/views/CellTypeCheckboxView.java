package br.com.iomarsantos.testeandroid.ui.fundo.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class CellTypeCheckboxView implements CellView {

    private View view;
    private Cell cell;
    private ContatoView contatoView;

    @BindView(R.id.checkbox_like_email)
    CheckBox checkboxLikeEmail;

    public CellTypeCheckboxView(ContatoView contatoView, Cell cell) {
        this.contatoView = contatoView;
        this.view = LayoutInflater.from(contatoView.getActivity()).inflate(R.layout.view_cell_type_checkbox, null);
        this.cell = cell;
        ButterKnife.bind(this, view);
        configuraView(cell);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void configuraView(Cell cell) {
        checkboxLikeEmail.setText(cell.getMessage());
        configureVisibility(cell);
    }

    private void configureVisibility(Cell cell) {
        if (cell.getHidden()) {
            this.view.setVisibility(View.GONE);
        }
    }

    @OnCheckedChanged(R.id.checkbox_like_email)
    public void checkbox(CheckBox cb, boolean checked) {
        if (cell.getShow() != null) {
            if (checked){
                this.contatoView.visibilityForCellTypeFieldEmailView(View.VISIBLE, cb);
            }else {
                this.contatoView.visibilityForCellTypeFieldEmailView(View.GONE, cb);
            }
        }
    }

}
