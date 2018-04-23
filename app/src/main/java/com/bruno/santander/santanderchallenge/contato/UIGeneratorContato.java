package com.bruno.santander.santanderchallenge.contato;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bruno.santander.santanderchallenge.R;
import com.bruno.santander.santanderchallenge.UIUtils;
import com.bruno.santander.santanderchallenge.contato.model.Cell;

public class UIGeneratorContato {

    public static TextView getTextView(Context context, Cell cell){
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(0, (int)cell.getTopSpacing(), 0, 20);
        TextView textView = new TextView(context);
        textView.setId(cell.getId());
        textView.setText(cell.getMessage());
        textView.setTag(cell.isRequired());
        textView.setLayoutParams(textParams);
        textView.setPadding(0, 0, 0, 10);
        if(cell.isHidden()){
            textView.setVisibility(View.GONE);
        }
        else{
            textView.setVisibility(View.VISIBLE);
        }

        return textView;
    }

    public static TextInputLayout getTextInputLayout(Context context, Cell cell){
        LinearLayout.LayoutParams editParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        editParams.setMargins(0, (int)cell.getTopSpacing(), 0, 0);
        EditText editText = new EditText(context);
        editText.setId(cell.getId());
        if(cell.getTypeField().equals("telnumber")){
            editText.setInputType(InputType.TYPE_CLASS_PHONE);
            UIUtils.setPhoneMaskListener(editText);
        }
        else{
            if(cell.getTypeField().equals("3")){
                editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            }
            else{
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        }
        editText.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        editText.setHint(cell.getMessage());
        editText.setTag(cell.isRequired());
        editText.setLayoutParams(editParams);
        if(cell.isHidden()){
            editText.setVisibility(View.GONE);
        }
        else{
            editText.setVisibility(View.VISIBLE);
        }

        LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        TextInputLayout textInputLayout = new TextInputLayout(context);
        textInputLayout.setLayoutParams(inputParams);
        textInputLayout.addView(editText);

        return textInputLayout;
    }

    public static AppCompatCheckBox getCheckBox(Context context, Cell cell){
        LinearLayout.LayoutParams checkBoxParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        checkBoxParams.setMargins(0, (int)cell.getTopSpacing(), 0, 20);
        AppCompatCheckBox checkBox = new AppCompatCheckBox(context);
        checkBox.setId(cell.getId());
        checkBox.setLayoutParams(checkBoxParams);
        checkBox.setText(cell.getMessage());
        checkBox.setTag(cell.getShow()); //O id do elemento a ser exibido se estiver checked
        checkBox.setTextColor(ContextCompat.getColor(context, R.color.text_color_hint));
        if(cell.isHidden()){
            checkBox.setVisibility(View.GONE);
        }
        else{
            checkBox.setVisibility(View.VISIBLE);
        }

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{
                        new int[]{-android.R.attr.state_checked}, // unchecked
                        new int[]{android.R.attr.state_checked} // checked
                },
                new int[]{
                        ContextCompat.getColor(context, R.color.text_color_hint),
                        ContextCompat.getColor(context, R.color.colorPrimary)
                }
        );

        CompoundButtonCompat.setButtonTintList(checkBox,colorStateList);

        return checkBox;
    }

    public static ImageView getImageView(Context context, Cell cell){
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageParams.setMargins(0, (int)cell.getTopSpacing(), 0, 0);
        ImageView imageView = new ImageView(context);
        imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.naodisponivel));
        imageView.setLayoutParams(imageParams);

        return imageView;
    }

    public static Button getButton(Context context, Cell cell){
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(0, (int)cell.getTopSpacing(), 0, 0);
        Button button = new Button(context);
        button.setId(cell.getId());
        button.setLayoutParams(buttonParams);
        button.setBackground(ContextCompat.getDrawable(context, R.drawable.button_selector));
        button.setText(cell.getMessage());
        button.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        if(cell.isHidden()){
            button.setVisibility(View.GONE);
        }
        else{
            button.setVisibility(View.VISIBLE);
        }

        return button;
    }

}
