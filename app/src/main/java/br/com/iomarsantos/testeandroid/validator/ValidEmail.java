package br.com.iomarsantos.testeandroid.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

public class ValidEmail implements Validator {

    private final TextInputLayout textInputEmail;
    private final EditText campoEmail;
    private final DefaultValidation defaultValidation;

    public ValidEmail(TextInputLayout textInputEmail) {
        this.textInputEmail = textInputEmail;
        this.campoEmail = this.textInputEmail.getEditText();
        this.defaultValidation = new DefaultValidation(this.textInputEmail);
    }

    private boolean defaultValidation(String email){
        if(email.matches(".+@.+\\..+")){
            return true;
        }
        textInputEmail.setError("E-mail inválido");
        return false;
    }

    @Override
    public boolean isValid(){
        if(!defaultValidation.isValid()) return false;
        String email = campoEmail.getText().toString();
        if(!defaultValidation(email)) return false;
        return true;
    }
}
