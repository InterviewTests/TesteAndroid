package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import android.view.View;
import android.widget.CheckBox;

import br.com.iomarsantos.testeandroid.ui.base.BaseView;

public interface ContatoView extends BaseView {
    void addView(View view);
    void configureEmailField(View view);
    void configuraPhoneField(View view);
    void configureTextField(View view);
    void visibilityForCellTypeFieldEmailView(int visibility, CheckBox checkBox);
    void send();
}
