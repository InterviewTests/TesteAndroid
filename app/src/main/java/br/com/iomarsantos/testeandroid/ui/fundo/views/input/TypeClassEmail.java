package br.com.iomarsantos.testeandroid.ui.fundo.views.input;

import android.text.InputType;

import br.com.iomarsantos.testeandroid.R;

public class TypeClassEmail implements ClassInputType {

    @Override
    public int getInputType() {
        return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
    }

    @Override
    public int getId() {
        return R.id.type_email;
    }

}