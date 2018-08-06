package br.com.iomarsantos.testeandroid.ui.fundo.views.cell;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CellTypeTextView implements CellView {

    private View view;

    @BindView(R.id.text_view_cell_text_message)
    TextView textViewMessage;

    public CellTypeTextView(ContatoView contatoView, Cell cell) {
        this.view = LayoutInflater.from(contatoView.getActivity()).inflate(R.layout.view_cell_type_text, null);
        ButterKnife.bind(this, view);
        configuraView(cell);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void configuraView(Cell cell) {
        textViewMessage.setText(cell.getMessage());
        configureVisibility(cell);
    }

    private void configureVisibility(Cell cell) {
        if (cell.getHidden()){
            this.view.setVisibility(View.GONE);
        }
    }

}
