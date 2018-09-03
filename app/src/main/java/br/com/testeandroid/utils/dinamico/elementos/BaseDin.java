package br.com.testeandroid.utils.dinamico.elementos;

import android.content.Context;
import android.support.annotation.StyleRes;
import android.text.InputType;
import android.widget.Button;
import android.widget.TextView;

import br.com.testeandroid.model.Cells;

import static br.com.testeandroid.utils.Constants.INPUT_TYPE_EMAIL;
import static br.com.testeandroid.utils.Constants.INPUT_TYPE_TEL_NUMBER;
import static br.com.testeandroid.utils.Constants.INPUT_TYPE_TEXT;

public class BaseDin {

    private Context context;

    public BaseDin(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public int getInputType(Cells c) {
        final String inputType = c.getTypeField();

        switch (inputType) {
            case INPUT_TYPE_TEXT:
                return InputType.TYPE_CLASS_TEXT;
            case INPUT_TYPE_TEL_NUMBER:
                return InputType.TYPE_CLASS_PHONE;
            case INPUT_TYPE_EMAIL:
                return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
            default:
                return InputType.TYPE_CLASS_TEXT;
        }
    }
}
