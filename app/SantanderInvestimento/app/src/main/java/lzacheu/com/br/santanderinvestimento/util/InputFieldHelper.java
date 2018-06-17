package lzacheu.com.br.santanderinvestimento.util;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import lzacheu.com.br.santanderinvestimento.model.contact.InputField;
import lzacheu.com.br.santanderinvestimento.widget.CustomEditText;

/**
 * Created by luiszacheu on 6/17/18.
 */

public class InputFieldHelper {

    public static View wrapChildOnTextInputLayout(Context context, View childView){
        TextInputLayout textInputLayout = new TextInputLayout(context);
        LinearLayout.LayoutParams textInputLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textInputLayout.setLayoutParams(textInputLayoutParams);
        textInputLayout.addView(childView, textInputLayoutParams);

        return textInputLayout;
    }

    public static CustomEditText createEditText(Context context, InputField inputField){

        CustomEditText editText = new CustomEditText(context);
        editText.setLayoutParams(getParams(inputField.getTopSpacing()));
        editText.setId(inputField.getId());
        editText.setHint(inputField.getMessage());
        editText.setRequired(inputField.getRequired());


        return editText;
    }

    public static TextView createTextView(Context context, InputField inputField){
        TextView textView = new TextView(context);
        textView.setId(inputField.getId());
        textView.setLayoutParams(getParams(inputField.getTopSpacing()));
        textView.setText(inputField.getMessage());
        textView.setTextSize(20);

        return textView;
    }

    public static CheckBox createCheckBox(Context context, InputField inputField){
        CheckBox checkBox = new CheckBox(context);
        checkBox.setId(inputField.getId());
        checkBox.setText(inputField.getMessage());
        checkBox.setLayoutParams(getParams(inputField.getTopSpacing()));

        return checkBox;
    }

    public static Button createButtom(Context context, InputField inputField){
        Button button = new Button(context);
        button.setId(inputField.getId());
        button.setText(inputField.getMessage());
        button.setLayoutParams(getParams(inputField.getTopSpacing()));

        return button;
    }

    private static LinearLayout.LayoutParams getParams(int topSpacing){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(0,topSpacing,0,0);
        return params;
    }

}
