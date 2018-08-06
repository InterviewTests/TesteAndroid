package br.com.iomarsantos.testeandroid.ui.fundo.views.input;

import android.text.InputType;

import br.com.iomarsantos.testeandroid.R;

public class TypeClassText implements ClassInputType {

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_TEXT;
    }

    @Override
    public int getId() {
        return R.id.type_text;
    }

}