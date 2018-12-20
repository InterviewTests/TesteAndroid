package com.seletiva.santander.investment.UI.View;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seletiva.santander.investment.Models.Cell;
import com.seletiva.santander.investment.R;

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

    private void configureCallToAction() {
        final Button button = findViewById(R.id.callToAction);
        button.setText(cellCore.getMessage());
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
        watcher.enablePhoneValidationMode();

        optionalTextField = findViewById(R.id.textualInputData);
        optionalTextField.addTextChangedListener(watcher);
    }

    private void configureClearButton() {
        ImageButton clearButton = findViewById(R.id.clearButton);
        final EditText textualInputData = findViewById(R.id.textualInputData);

        clearButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                textualInputData.setText(null);
            }
        });
    }

    @Override
    public void updateEditTextColor(int color) {
        optionalTextField.getBackground().setColorFilter(getResources().getColor(color),
                PorterDuff.Mode.SRC_ATOP);
    }
}
