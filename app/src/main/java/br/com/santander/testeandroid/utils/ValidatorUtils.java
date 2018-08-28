package br.com.santander.testeandroid.utils;

import android.text.InputType;
import android.widget.EditText;

import java.util.List;

public class ValidatorUtils {

    public static boolean validadeFields(List<EditText> views) {
        boolean successfull = true;

        for (EditText editText : views) {
            String textToValidade = editText.getText().toString();

            switch (editText.getInputType()) {

                case InputType.TYPE_CLASS_TEXT:
                    if (textToValidade.isEmpty() || !textToValidade.contains(" ")) {
                        editText.setError(Constants.INVALID_FIELD);
                        successfull = false;
                    }
                    break;
                case InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS:
                    if (!matchEmail(textToValidade)) {
                        editText.setError(Constants.INVALID_FIELD);
                        successfull = false;
                    }
                    break;
                case InputType.TYPE_CLASS_PHONE:
                    if (!matchPhoneNumber(textToValidade)) {
                        editText.setError(Constants.INVALID_FIELD);
                        successfull = false;
                    }
                    break;
            }
        }
        return successfull;
    }

    private static boolean matchPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\(\\d\\d\\)\\d\\d\\d\\d\\-\\d\\d\\d\\d") ||
                phoneNumber.matches("\\(\\d\\d\\)\\d\\d\\d\\d\\d\\-\\d\\d\\d\\d");
    }

    private static boolean matchEmail(String email) {
        return email.matches("^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$");
    }

}
