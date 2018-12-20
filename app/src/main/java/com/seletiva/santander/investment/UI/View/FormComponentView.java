package com.seletiva.santander.investment.UI.View;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

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
                    configureClearButton();
                    configureTextualDataWatcher();
                    break;
                case text:
                    inflate(getContext(), R.layout.form_component_text, this);
                    break;
                default:
                    break;
            }
        }
    }

    private void configureTextualDataWatcher() {
        optionalTextField = findViewById(R.id.textualInputData);
        optionalTextField.addTextChangedListener(new FormFieldWatcher(this));
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
