package com.seletiva.santander.investment.ui.view;

import android.text.Editable;
import android.text.TextWatcher;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.utils.StringUtils;

import static com.seletiva.santander.investment.utils.StringUtils.REGULAR_PHONE_LONG_NUMBER_LENGTH;
import static com.seletiva.santander.investment.utils.StringUtils.REGULAR_PHONE_NUMBER_LENGTH;

/**
 * Watcher de campo de texto para valores de email e telefone
 */
public class FormFieldWatcher implements TextWatcher {
    private FormFieldListener formFieldListener;
    private boolean processing = false;
    private boolean mailValidation = false;
    private boolean phoneValidation = false;

    FormFieldWatcher(FormFieldListener listener) {
        formFieldListener = listener;
    }

    /**
     * Habilita TextWatcher para validacao de email
     */
    public void enableMailValidationMode() {
        mailValidation = true;
        phoneValidation = false;
    }

    /**
     * Habilita TextWatcher para telefone de email
     */
    public void enablePhoneValidationMode() {
        mailValidation = false;
        phoneValidation = true;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence text, int i, int i1, int i2) {
        if (mailValidation) {
            colorfyMailField(text.toString());
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (processing)
            return;


        processing = true;

        if (phoneValidation) {
            final String unformatted = StringUtils.turnFormattedPhoneNumberToRawString(editable.toString());
            final String formatted = StringUtils.formatAsPhoneNumber(unformatted);
            editable.clear();
            editable.append(formatted);
            colorfyPhoneField(unformatted);
        }

        processing = false;
    }

    private void colorfyMailField(String mail) {
        boolean isMailValid = StringUtils.validateEmailAdress(mail);

        int colorId = isMailValid ? R.color.validatedColor : R.color.colorAccent;
        formFieldListener.updateEditTextColor(colorId);
    }

    private void colorfyPhoneField(String phone) {
        boolean isFieldValid = false;

        if (phone.length() == REGULAR_PHONE_NUMBER_LENGTH ||
                phone.length() == REGULAR_PHONE_LONG_NUMBER_LENGTH) {
            isFieldValid = true;
        }

        int colorId = isFieldValid ? R.color.validatedColor : R.color.colorAccent;
        formFieldListener.updateEditTextColor(colorId);
    }
}
