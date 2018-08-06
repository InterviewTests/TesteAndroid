package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import android.widget.CheckBox;

import java.util.List;

import br.com.iomarsantos.testeandroid.entity.Cell;
import br.com.iomarsantos.testeandroid.ui.base.BaseView;

public interface ContatoView extends BaseView {
    void createViews(List<Cell> cells);

    void visibilityForCellTypeFieldEmailView(int visibility, CheckBox checkBox);

    void send();
}
