package br.com.iomarsantos.testeandroid.ui.fundo.views.input;

import android.text.InputType;

public class TypeClassText implements ClassInputType {

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_TEXT;
    }

}