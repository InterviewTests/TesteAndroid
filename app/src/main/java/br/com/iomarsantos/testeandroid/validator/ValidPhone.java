package br.com.iomarsantos.testeandroid.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.iomarsantos.testeandroid.formatter.PhoneFormat;

public class ValidPhone implements Validator {

    public static final String VALIDA_PHONE_MESSAGE = "O Numero do telefone precisa ter entre 10 a 11 dígitos";

    private final TextInputLayout textInputPhone;
    private final EditText fieldPhone;
    private final DefaultValidation defaultValidation;
    private final PhoneFormat phoneFormat = new PhoneFormat();

    public ValidPhone(TextInputLayout textInputTelefoneComDdd) {
        this.textInputPhone = textInputTelefoneComDdd;
        this.fieldPhone = textInputTelefoneComDdd.getEditText();
        this.defaultValidation = new DefaultValidation(textInputTelefoneComDdd);
    }

    private boolean validBetweenDdigits(String phone){
        int digitos = phone.length();
        if(digitos < 10 || digitos > 11){
            textInputPhone.setError(VALIDA_PHONE_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean isValid(){
        if(!defaultValidation.isValid()) return false;
        String phone = fieldPhone.getText().toString();
        String phoneWithoutFormatting = phoneFormat.remove(phone);
        if(!validBetweenDdigits(phoneWithoutFormatting)) return false;
        addFormatting(phoneWithoutFormatting);
        return true;
    }

    private void addFormatting(String phone) {
        String telefoneComDddFormatado = phoneFormat.format(phone);
        fieldPhone.setText(telefoneComDddFormatado);
    }

}
