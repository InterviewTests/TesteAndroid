package br.com.iomarsantos.testeandroid.ui.fundo.views.input;

import android.text.InputType;

public class TypeClassPhone implements ClassInputType {

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_PHONE;
    }

}