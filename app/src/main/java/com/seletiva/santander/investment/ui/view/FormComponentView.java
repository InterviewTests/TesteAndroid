package com.seletiva.santander.investment.ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seletiva.santander.investment.ui.form.domain.model.Cell;
import com.seletiva.santander.investment.ui.form.domain.model.CellType;
import com.seletiva.santander.investment.ui.form.domain.model.SendButtonClickEvent;
import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.utils.RightDrawableOnTouchListener;
import com.seletiva.santander.investment.utils.StringUtils;

import org.greenrobot.eventbus.EventBus;

import static com.seletiva.santander.investment.utils.StringUtils.FORMATTED_MAX_PHONE_LENGTH;

/**
 * CustomView utilizado para criacao de cada componente constituinte do formulario de
 * contato.
 */
public class FormComponentView extends LinearLayout implements FormFieldListener {
    private Cell cellCore;
    private EditText optionalTextField;

    public FormComponentView(Context context) {
        super(context);
    }

    public FormComponentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FormComponentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void inflateWithCellModel(Cell core) {
        cellCore = core;

        if (core != null) {
            switch (cellCore.getType()) {
                case field:
                    inflate(getContext(), R.layout.form_component_field, this);
                    configureComponentTitle();
                    configureClearButton();
                    configureTextualDataWatcher();
                    configureKeyboardType();
                    break;

                case text:
                    inflate(getContext(), R.layout.form_component_text, this);
                    configureTextualDisplayData();
                    break;

                case checkbox:
                    inflate(getContext(), R.layout.form_component_checkbox, this);
                    configureCheckBoxData();
                    break;

                case send:
                    inflate(getContext(), R.layout.form_component_button, this);
                    configureCallToAction();
                    break;

                default:
                    break;
            }
        }
    }

    private void configureKeyboardType() {
        switch (cellCore.getTypeField()) {
            case telNumber:
                optionalTextField.setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case email:
                optionalTextField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            default:
                break;
        }
    }

    private void configureCallToAction() {
        final Button button = findViewById(R.id.callToAction);
        button.setText(cellCore.getMessage());

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(SendButtonClickEvent.newClickEvent());
            }
        });
    }

    private void configureCheckBoxData() {
        final CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setText(cellCore.getMessage());
    }

    private void configureTextualDisplayData() {
        final TextView title = findViewById(R.id.textualDisplayData);
        title.setText(cellCore.getMessage());
    }

    private void configureComponentTitle() {
        final TextView title = findViewById(R.id.componentTitle);
        title.setText(cellCore.getMessage());
    }

    private void configureTextualDataWatcher() {
        FormFieldWatcher watcher = new FormFieldWatcher(this);
        optionalTextField = findViewById(R.id.textualInputData);

        switch (cellCore.getTypeField()) {
            case telNumber:
                optionalTextField
                        .setFilters(new InputFilter[]{
                                new InputFilter.LengthFilter(FORMATTED_MAX_PHONE_LENGTH)
                        });
                watcher.enablePhoneValidationMode();
                break;
            case email:
                watcher.enableMailValidationMode();
                break;
            default:
                break;
        }


        optionalTextField.addTextChangedListener(watcher);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void configureClearButton() {
        final EditText textualInputData = findViewById(R.id.textualInputData);

        textualInputData.setOnTouchListener(new RightDrawableOnTouchListener(textualInputData) {
            @Override
            public boolean onDrawableTouch(final MotionEvent event) {
                textualInputData.setText(null);
                return true;
            }
        });
    }

    @Override
    public void updateEditTextColor(int color) {
        optionalTextField.getBackground().setColorFilter(getResources().getColor(color),
                PorterDuff.Mode.SRC_ATOP);
    }

    /**
     * Verifica se o campo foi preenchido com informações válidas (se necessario)
     * @return true se o campo contem informações válidas; false, caso contrario
     */
    public boolean isValid() {
        if (cellCore.getType() == CellType.field) {
            String inputData = optionalTextField.getText().toString();
            boolean isValid;

            switch (cellCore.getTypeField()) {
                case telNumber:
                    isValid = StringUtils.isPhoneNumberValid(inputData);
                    showErrorMessageIfNecessary(isValid);
                    return isValid;

                case email:
                    isValid = StringUtils.validateEmailAdress(inputData);
                    showErrorMessageIfNecessary(isValid);
                    return isValid;

                default:
                    if (inputData.length() == 0) {
                        showErrorMessageIfNecessary(false);
                        return false;
                    }
                    break;
            }
        }

        return true;
    }

    private void showErrorMessageIfNecessary(boolean isValid) {
        if (!isValid) {
            optionalTextField.setError(getResources().getString(R.string.field_fixit));
        }
    }

    /**
     * Remove o conteudo - informacao inserida - da componente (se necessario)
     */
    public void clearComponentIfNecessary() {
        if (cellCore.getType() == CellType.field) {
            optionalTextField.setText(null);
            optionalTextField.getBackground().setColorFilter(getResources().getColor(R.color.colorGrey),
                    PorterDuff.Mode.SRC_ATOP);
        } else if(cellCore.getType() == CellType.checkbox) {
            CheckBox checkBox = findViewById(R.id.checkBox);
            checkBox.setChecked(false);
        }
    }
}
