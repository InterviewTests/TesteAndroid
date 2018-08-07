package resource.com.br.santanderapp.validator;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import resource.com.br.santanderapp.R;

public class ValidatorStandard implements Validator {
    private final TextInputLayout tilField;
    private final EditText field;
    private final Context context;

    public ValidatorStandard(TextInputLayout tilField, Context context) {
        this.tilField = tilField;
        this.field = tilField.getEditText();
        this.context = context;
    }

    private boolean validateFieldRequired() {
        String text = field.getText().toString();
        if (text.isEmpty()) {
            tilField.setError(context.getString(R.string.validator_starndard_txt_field_required));
            return false;
        }
        return true;
    }

    private void removeError() {
        tilField.setError(null);
        tilField.setErrorEnabled(false);
    }

    @Override
    public boolean isValid() {
        if (!validateFieldRequired()) return false;
        removeError();
        return true;
    }
}
