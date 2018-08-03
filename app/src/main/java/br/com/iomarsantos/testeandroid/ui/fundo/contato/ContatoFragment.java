package br.com.iomarsantos.testeandroid.ui.fundo.contato;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.di.component.ActivityComponent;
import br.com.iomarsantos.testeandroid.formatter.PhoneFormat;
import br.com.iomarsantos.testeandroid.ui.base.BaseFragment;
import br.com.iomarsantos.testeandroid.validator.DefaultValidation;
import br.com.iomarsantos.testeandroid.validator.ValidEmail;
import br.com.iomarsantos.testeandroid.validator.ValidPhone;
import br.com.iomarsantos.testeandroid.validator.Validator;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContatoFragment extends BaseFragment implements
        ContatoView {

    private static final String TAG = "ContatoFragment";

    @Inject
    ContatoBasePresenter<ContatoView> mPresenter;

    @BindView(R.id.linear_layout_contato_container)
    LinearLayout layoutContainer;

    @BindView(R.id.view_flipper)
    ViewFlipper viewFlipper;

    private final List<Validator> validators = new ArrayList<>();

    private View cellTypeFieldEmailView;
    private ValidEmail validEmail;
    private EditText phoneField;
    private EditText emailField;
    private EditText textField;
    private CheckBox checkBoxEmail;

    public static ContatoFragment newInstance() {
        Bundle args = new Bundle();
        ContatoFragment fragment = new ContatoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contato, container, false);
        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            setUnBinder(ButterKnife.bind(this, view));
            mPresenter.onAttach(this);
        }
        return view;
    }

    @Override
    public void setup() {
        mPresenter.findAllCellsApiCall();
    }

    @Override
    public void addView(View view) {
        this.layoutContainer.addView(view);
    }

    @Override
    public void configureEmailField(View view) {
        this.cellTypeFieldEmailView = view;
        TextInputLayout textInputEmail = view.findViewById(R.id.text_input_layout_cell_type_field);
        emailField = textInputEmail.getEditText();
        validEmail = new ValidEmail(textInputEmail);
        emailField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validEmail.isValid();
                }
            }
        });
    }

    @Override
    public void configuraPhoneField(View view) {
        TextInputLayout textInputPhone = view.findViewById(R.id.text_input_layout_cell_type_field);
        phoneField = textInputPhone.getEditText();
        final ValidPhone validator = new ValidPhone(textInputPhone);
        validators.add(validator);
        final PhoneFormat formatter = new PhoneFormat();
        phoneField.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDdd = phoneField.getText().toString();
                if (hasFocus) {
                    String phoneWithNoFormat = formatter.remove(telefoneComDdd);
                    phoneField.setText(phoneWithNoFormat);
                } else {
                    validator.isValid();
                }
            }
        });
    }

    @Override
    public void configureTextField(View view) {
        TextInputLayout textInputName = view.findViewById(R.id.text_input_layout_cell_type_field);
        textField = textInputName.getEditText();
        addDefaultValidation(textInputName);
    }


    @Override
    public void visibilityForCellTypeFieldEmailView(final int visibility, CheckBox checkBox) {
        this.checkBoxEmail = checkBox;
        if (visibility == View.GONE){
            validators.remove(validEmail);
        }else {
            validators.add(validEmail);
        }
        this.cellTypeFieldEmailView.setVisibility(visibility);
    }

    @Override
    public void send() {
        boolean formIsValid = validAllFields();
        if(formIsValid){
            viewFlipper.showNext();
            clearFields();
        }
    }

    private void clearFields(){
        this.textField.setText("");
        this.emailField.setText("");
        this.phoneField.setText("");
        if (this.checkBoxEmail != null) {
            this.checkBoxEmail.setChecked(false);
        }
        visibilityForCellTypeFieldEmailView(View.GONE, this.checkBoxEmail);
    }

    @OnClick(R.id.button_contato_new_message)
    public void sendNewMessage() {
        viewFlipper.showPrevious();
    }

    private boolean validAllFields() {
        boolean formIsValid = true;
        for (Validator validator : validators) {
            if(!validator.isValid()){
                formIsValid = false;
            }
        }
        return formIsValid;
    }

    private void addDefaultValidation(final TextInputLayout textInputLayoutField) {
        final EditText field = textInputLayoutField.getEditText();
        final DefaultValidation validator = new DefaultValidation(textInputLayoutField);
        validators.add(validator);
        field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validator.isValid();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

}
