package resource.com.br.santanderapp.ui.form;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import resource.com.br.santanderapp.R;
import resource.com.br.santanderapp.mask.PhoneMask;
import resource.com.br.santanderapp.validator.Validator;
import resource.com.br.santanderapp.validator.ValidatorEmail;
import resource.com.br.santanderapp.validator.ValidatorPhone;
import resource.com.br.santanderapp.validator.ValidatorStandard;

public class FormFragment extends Fragment {

    private final List<Validator> validators = new ArrayList<>();
    private ViewPager viewPager;

    public FormFragment() {

    }

    @SuppressLint("ValidFragment")
    public FormFragment(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.form_fragment, container, false);




        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final List<Validator> validators = new ArrayList<>();
        initializeFields(view);
    }

    private void initializeFields(@NonNull View view) {
        setFieldName(view);
        setFieldEmail(view);
        setFieldPhone(view);
        setButtonSend(view);
    }

    private void setFieldName(View view) {
        TextInputLayout txtName = view.findViewById(R.id.form_txt_name);
        EditText name = txtName.getEditText();
        validateEmptyField(txtName);
    }

    private void setFieldEmail(View view) {
        TextInputLayout txtEmail = view.findViewById(R.id.form_txt_email);
        EditText fieldEMail = txtEmail.getEditText();
        final ValidatorEmail validator = new ValidatorEmail(txtEmail, getContext());
        validators.add(validator);
        fieldEMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });

    }

    private void setFieldPhone(View view) {
        TextInputLayout txtPhone = view.findViewById(R.id.form_txt_phone);
        final EditText fieldPhone = txtPhone.getEditText();
        final ValidatorPhone validator = new ValidatorPhone(txtPhone, getContext());
        final PhoneMask phoneMask = new PhoneMask();
        validators.add(validator);
        fieldPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String phoneWithMask = fieldPhone.getText().toString();
                if (hasFocus) {
                    String phoneWithoutMask = phoneMask.unMaskPhone(phoneWithMask);
                    fieldPhone.setText(phoneWithoutMask);
                } else {
                    validator.isValid();
                }
            }
        });
    }

    private void validateEmptyField(TextInputLayout txtName) {
        EditText fieldName = txtName.getEditText();
        final ValidatorStandard validator = new ValidatorStandard(txtName, getContext());
        validators.add(validator);
        fieldName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });

    }

    public void setButtonSend(View view) {
        Button btnSend = view.findViewById(R.id.form_btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean formIsValid = validAllFields();
                if (formIsValid) {
                    viewPager.setCurrentItem(2);
                }
            }
        });
    }

    private boolean validAllFields() {
        boolean formIsValid = true;
        for (Validator validator : validators) {
            if (!validator.isValid()) {
                formIsValid = false;
            }
        }
        return formIsValid;
    }
}
