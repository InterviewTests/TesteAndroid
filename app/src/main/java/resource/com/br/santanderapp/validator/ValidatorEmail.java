package resource.com.br.santanderapp.validator;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import resource.com.br.santanderapp.R;

public class ValidatorEmail implements Validator {
    private final TextInputLayout tilEmail;
    private final EditText fieldEmail;
    private final ValidatorStandard standardValidator;
    private final Context context;


    public ValidatorEmail(TextInputLayout tilEmail, Context context) {
        this.tilEmail = tilEmail;
        this.fieldEmail = tilEmail.getEditText();
        this.standardValidator = new ValidatorStandard(this.tilEmail, context);
        this.context = context;

    }

    private boolean standardValidate(String email) {
        if (email.matches(".+@.+\\..+")) {
            return true;
        }
        tilEmail.setError(context.getString(R.string.form_txt_error_email));
        return false;
    }

    @Override
    public boolean isValid() {
        if (!standardValidator.isValid()) return false;
        String email = fieldEmail.getText().toString();
        if (!standardValidate(email)) return false;
        return true;
    }
}
