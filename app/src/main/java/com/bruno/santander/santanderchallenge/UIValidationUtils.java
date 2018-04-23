package com.bruno.santander.santanderchallenge;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.List;

public class UIValidationUtils {

    public static boolean validateEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean validateEditTexts(Context context, CoordinatorLayout coordinatorLayout, List<EditText> editTexts){
        for(final EditText editText : editTexts){
            if(editText.getVisibility() == View.VISIBLE && (boolean)editText.getTag()) {
                String editString = editText.getText().toString();

                FrameLayout frameLayout = (FrameLayout) editText.getParent();
                TextInputLayout til = (TextInputLayout) frameLayout.getParent();

                if (editString.equals("")) {
                    if(til.getHint() != null){
                        Snackbar.make(coordinatorLayout, til.getHint().toString() + " é obrigatório", Snackbar.LENGTH_LONG).show();
                    }

                    UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.error_field));
                    editText.setHighlightColor(ContextCompat.getColor(context, R.color.error_field));
                    editText.requestFocus();
                    return false;
                }
                else{
                    UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.valid_field));
                    editText.setHighlightColor(ContextCompat.getColor(context, R.color.valid_field));
                }

                if (editText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                    if (!validateEmail(editString)) {
                        Snackbar.make(coordinatorLayout, context.getString(R.string.email_invalido), Snackbar.LENGTH_LONG).show();

                        UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.error_field));
                        editText.setHighlightColor(ContextCompat.getColor(context, R.color.error_field));
                        editText.requestFocus();
                        return false;
                    }
                    else{
                        UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.valid_field));
                        editText.setHighlightColor(ContextCompat.getColor(context, R.color.valid_field));
                    }
                }

                if (editText.getInputType() == InputType.TYPE_CLASS_PHONE) {
                    if(editString.length() < 13 || editString.length() > 14){
                        Snackbar.make(coordinatorLayout, context.getString(R.string.telefone_invalido), Snackbar.LENGTH_LONG).show();

                        UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.error_field));
                        editText.setHighlightColor(ContextCompat.getColor(context, R.color.error_field));
                        editText.requestFocus();
                        return false;
                    }
                    else{
                        UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.valid_field));
                        editText.setHighlightColor(ContextCompat.getColor(context, R.color.valid_field));
                    }
                }
            }
        }

        return true;
    }

    public static void validateSingleEditText(Context context, EditText editText){
        String editString = editText.getText().toString();

        if (editString.equals("")) {
            UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.error_field));
            editText.setHighlightColor(ContextCompat.getColor(context, R.color.error_field));
        }
        else if(editText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS){
            if(!validateEmail(editString)){
                UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.error_field));
                editText.setHighlightColor(ContextCompat.getColor(context, R.color.error_field));
            }
            else{
                UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.valid_field));
                editText.setHighlightColor(ContextCompat.getColor(context, R.color.valid_field));
            }
        }
        else if(editText.getInputType() == InputType.TYPE_CLASS_PHONE){
            if(editString.length() < 13 || editString.length() > 14){
                UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.error_field));
                editText.setHighlightColor(ContextCompat.getColor(context, R.color.error_field));
            }
            else{
                UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.valid_field));
                editText.setHighlightColor(ContextCompat.getColor(context, R.color.valid_field));
            }
        }
        else{
            UIUtils.setInputTextLayoutColor(editText, ContextCompat.getColor(context, R.color.valid_field));
            editText.setHighlightColor(ContextCompat.getColor(context, R.color.valid_field));
        }

    }


}
