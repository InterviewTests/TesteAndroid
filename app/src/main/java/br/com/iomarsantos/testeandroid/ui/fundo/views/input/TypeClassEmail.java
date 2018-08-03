package br.com.iomarsantos.testeandroid.ui.fundo.views.input;

import android.text.InputType;

public class TypeClassEmail implements ClassInputType {

    @Override
    public int getInputType() {
        return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
    }

}