package br.banco.services.app.utils;

import java.util.regex.Pattern;

/*
FEITO: 0000000000 / telefone com 10 digitos
@FAZER: Formatar (00)0000-0000
 */
public class ValidatorPhone  { //implements TextWatcher

    private boolean misValid = false;
    public static final Pattern USER_NAME = Pattern.compile(
            "[0-9\\-\\+]{10,13}"
    );

    public static boolean isPhoneNumeric(String strTelefone) {
        try {

            String removeSpace = strTelefone.replace(" ","").trim();
            return strTelefone != null && USER_NAME.matcher(strTelefone).matches();

        }catch (NumberFormatException nfe) {
            return false;
        }catch (NullPointerException npe) {
            return false;
        }
    }

    public boolean isValid() {
        return misValid;
    }

    /*
    @Override
    final public void afterTextChanged(Editable editableText) {
    }
    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {}
    */

}
