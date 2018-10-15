package br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.cells;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import br.com.itamarlourenco.santandertecnologia_testeandroid.R;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.Cell;
import br.com.itamarlourenco.santandertecnologia_testeandroid.model.TypeField;
import br.com.itamarlourenco.santandertecnologia_testeandroid.util.MaskEditUtil;
import br.com.itamarlourenco.santandertecnologia_testeandroid.widgets.CustomEditText;

public class FieldCell extends BaseCell {

    private CustomEditText customEditText;

    public FieldCell(Context context, Cell cell, ViewGroup viewGroup, BaseCell.OnClickListener onClickListener) {
        super(context, cell, viewGroup, onClickListener);
    }

    @Override
    public int idLayout() {
        return R.layout.layout_widget_cell_field;
    }

    @Override
    protected void handleWithView(View view) {
        customEditText = view.findViewById(R.id.textView);
        LinearLayout line = view.findViewById(R.id.line);

        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        textInputLayout.setHint(getCell().getMessage());

        if(getCell().getTypeField() != null && !TextUtils.isEmpty(getCell().getTypeField().getFormat())){
            customEditText.addTextChangedListener(MaskEditUtil.mask(customEditText, getCell().getTypeField().getFormat()));
        }

        if(getCell().isRequired()){
            customEditText.setOnFocusChangeListener(new ValidateRequired(line));
        }

        setStyleKeyboard(customEditText);

        view.findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customEditText.setText("");
            }
        });

        customEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getCell().setValue(customEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        customEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                getCell().setValue(customEditText.getText().toString());
                return false;
            }
        });
    }

    private void setStyleKeyboard(CustomEditText customEditText) {
        if(getCell().getTypeField() == TypeField.telNumber) {
            customEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    private class ValidateRequired implements View.OnFocusChangeListener {
        private LinearLayout line;

        ValidateRequired(LinearLayout line) {
            this.line = line;
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus){
                if(!getCell().isValidData()){
                    line.setBackgroundColor(getContext().getColor(R.color.line_custom_edit_text_error));
                }else{
                    line.setBackgroundColor(getContext().getColor(R.color.line_custom_edit_text_success));
                }
            }
        }


    }
}
