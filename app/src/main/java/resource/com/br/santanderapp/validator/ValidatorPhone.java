package resource.com.br.santanderapp.validator;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import resource.com.br.santanderapp.R;
import resource.com.br.santanderapp.mask.PhoneMask;

public class ValidatorPhone implements Validator {
    private final TextInputLayout tilPhone;
    private final EditText fieldPhone;
    private final ValidatorStandard standardValidator;
    private final Context context;
    private final PhoneMask phoneMask = new PhoneMask();

    public ValidatorPhone(TextInputLayout tilPhone, Context context) {
        this.tilPhone = tilPhone;
        this.fieldPhone = tilPhone.getEditText();
        this.context = context;
        this.standardValidator = new ValidatorStandard(tilPhone, context);
    }

    private boolean validateNumberOfDigits(String phone) {
        int digitos = phone.length();
        if (digitos < 10 || digitos > 11) {
            tilPhone.setError(context.getString(R.string.form_txt_error_phone));
            return false;
        }
        return true;
    }

    public boolean isValid() {
        if (!standardValidator.isValid()) return false;
        String phone = fieldPhone.getText().toString();
        String phoneWithoutMask = phoneMask.unMaskPhone(phone);
        if (!validateNumberOfDigits(phoneWithoutMask)) return false;
        addMask(phoneWithoutMask);
        return true;
    }

    private void addMask(String phone) {
        String phoneWithMask = phoneMask.phoneMask(phone);
        fieldPhone.setText(phoneWithMask);
    }
}
