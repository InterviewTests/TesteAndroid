package br.com.iomarsantos.testeandroid.ui.fundo.views.cell;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CellTypeButtonView implements CellView {

    private View view;

    @BindView(R.id.button_cell_type_button_send)
    Button buttonSend;

    private ContatoView contatoView;

    public CellTypeButtonView(ContatoView contatoView, Cell cell) {
        this.contatoView = contatoView;
        this.view = LayoutInflater.from(contatoView.getActivity()).inflate(R.layout.view_cell_type_button, null);
        ButterKnife.bind(this, view);
        configuraView(cell);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void configuraView(Cell cell) {
        buttonSend.setText(cell.getMessage());
        configureVisibility(cell);
    }

    private void configureVisibility(Cell cell) {
        if (cell.getHidden()){
            this.view.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.button_cell_type_button_send)
    public void buttonSend() {
        this.contatoView.send();
    }

}
