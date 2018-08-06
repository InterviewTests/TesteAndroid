package br.com.iomarsantos.testeandroid.ui.fundo.views.input;

import android.text.InputType;

import br.com.iomarsantos.testeandroid.R;

public class TypeClassPhone implements ClassInputType {

    @Override
    public int getInputType() {
        return InputType.TYPE_CLASS_PHONE;
    }

    @Override
    public int getId() {
        return R.id.type_phone;
    }

}