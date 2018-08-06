package br.com.iomarsantos.testeandroid.ui.fundo.views.cell;

import android.view.LayoutInflater;
import android.view.View;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoView;
import butterknife.ButterKnife;

public class CellTypeImageView implements CellView {

    private View view;

    public CellTypeImageView(ContatoView context, Cell cell) {
        this.view = LayoutInflater.from(context.getActivity()).inflate(R.layout.view_cell_type_image, null);
        ButterKnife.bind(view);
    }

    @Override
    public View getView() {
        return this.view;
    }

    @Override
    public void configuraView(Cell cell) {

    }

}
