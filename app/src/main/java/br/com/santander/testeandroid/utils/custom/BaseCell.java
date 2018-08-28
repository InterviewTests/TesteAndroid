package br.com.santander.testeandroid.utils.custom;

import android.content.Context;
import android.support.annotation.StyleRes;
import android.text.InputType;
import android.widget.Button;
import android.widget.TextView;

import br.com.santander.testeandroid.contact.model.CellResponse;

import static br.com.santander.testeandroid.utils.Constants.INPUT_TYPE_EMAIL;
import static br.com.santander.testeandroid.utils.Constants.INPUT_TYPE_TEL_NUMBER;
import static br.com.santander.testeandroid.utils.Constants.INPUT_TYPE_TEXT;

public class BaseCell {

    private Context context;

    public BaseCell(Context context) {
        this.context = context;
    }

    public void setStyle(TextView view, @StyleRes int resId) {
        view.setTextAppearance(context, resId);
    }

    public void setStyle(Button view, @StyleRes int resId) {
        view.setTextAppearance(context, resId);
    }

    public Context getContext() {
        return context;
    }

    public int getInputType(CellResponse c) {
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
