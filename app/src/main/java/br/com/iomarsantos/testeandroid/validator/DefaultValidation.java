package br.com.iomarsantos.testeandroid.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class DefaultValidation implements Validator {

    private static final String CAMPO_OBRIGATORIO = "Campo obrigatório";
    private final TextInputLayout textInputCampo;
    private final EditText field;

    public DefaultValidation(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.field = this.textInputCampo.getEditText();
    }

    private boolean validateRequiredField() {
        String texto = field.getText().toString();
        if (texto.isEmpty()) {
            textInputCampo.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid(){
        if(!validateRequiredField()) return false;
        removeError();
        return true;
    }

    private void removeError() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }

}
