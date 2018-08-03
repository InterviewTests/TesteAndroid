package br.com.iomarsantos.testeandroid.ui.fundo.views;

import android.content.Context;

import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.entity.Type;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoPresenter;
import br.com.iomarsantos.testeandroid.ui.fundo.contato.ContatoView;

public class CellType {

    @Type
    private Integer type;

    @Type
    Integer getType() {
        return type;
    }

    private void setType(@Type Integer type) {
        this.type = type;
    }

    public CellView get(Cell cell, ContatoView contatoView) {
        setType(cell.getType());
        return getView(getType(), cell, contatoView);
    }

    private CellView getView(@Type Integer typeView, Cell cell, ContatoView contatoView) {
        CellView view = null;
        if (typeView != null) {
            switch (typeView) {
                case Type.TEXT:
                    view = new CellTypeTextView(contatoView, cell);
                    break;
                case Type.FIELD:
                    view = new CellTypeFieldView(contatoView, cell);
                    break;
                case Type.CHECKBOX:
                    view = new CellTypeCheckboxView(contatoView, cell);
                    break;
                case Type.BUTTON:
                    view = new CellTypeButtonView(contatoView, cell);
                    break;
                case Type.IMAGE:
                    view = new CellTypeImageView(contatoView, cell);
                    break;
            }
        }
        return view;
    }

}
