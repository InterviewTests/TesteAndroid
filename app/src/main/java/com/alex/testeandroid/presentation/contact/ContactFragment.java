package com.alex.testeandroid.presentation.contact;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alex.testeandroid.R;
import com.alex.testeandroid.data.entities.contact.Cell;
import com.alex.testeandroid.data.entities.contact.Type;
import com.alex.testeandroid.data.entities.contact.TypeField;
import com.alex.testeandroid.presentation.common.TextMask;
import com.alex.testeandroid.presentation.helpers.DimenHelper;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Alex on 27/08/18.
 */
public class ContactFragment extends Fragment implements ContactView {

    //region FIELDS
    private String name, email, phone;
    private ContactPresenter presenter;
    private ConstraintLayout consForm;
    private ProgressBar pgbLoading;
    //endregion

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

    //region LIFECYCLE
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        consForm = view.findViewById(R.id.fragment_contact_cons_form);
        pgbLoading = view.findViewById(R.id.fragment_contact_pgb_loading);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new ContactPresenter(this);
        presenter.getContactForm();
    }

    @Override
    public void onDestroyView() {
        if (presenter != null) presenter.detachView();
        super.onDestroyView();
    }
    //endregion

    //region METHODS
    //region OVERRIDES METHODS
    @Override
    public void showProgress(boolean show) {
        pgbLoading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMessageErrorRequest() {
        Toast.makeText(getContext(), "falha ao montar formulário", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setupCells(List<Cell> cells) {
        DimenHelper dimenHelper = new DimenHelper();
        ConstraintLayout.LayoutParams params;
        ConstraintSet constraintSet;
        for (Cell cell : cells) {
            switch (cell.getType().getId()) {
                case Type.FIELD:
                    ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), R.style.TextInput);
                    final TextInputLayout textInputLayout = new TextInputLayout(contextThemeWrapper);
                    textInputLayout.setTag(cell);
                    textInputLayout.setErrorEnabled(true);
                    textInputLayout.setId(cell.getId());
                    textInputLayout.setOrientation(LinearLayout.HORIZONTAL);
                    textInputLayout.setHintTextAppearance(R.style.TextInputHint);
                    textInputLayout.setErrorTextAppearance(R.style.TextInputError);
                    textInputLayout.setVisibility(cell.isHidden() && !cell.isRequired() ? View.GONE : View.VISIBLE);

                    TextInputEditText textInputEditText = new TextInputEditText(new ContextThemeWrapper(getContext(), R.style.TextInput));
                    textInputEditText.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro-Medium.otf"));
                    textInputEditText.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_field_contact_text));
                    textInputEditText.setHint(cell.getMessage());
                    textInputEditText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.grey_field_contact_hint));

                    switch (cell.getTypefield().getId()) {
                        case TypeField.TEXT:
                            textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                            textInputEditText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    name = s.toString();
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    textInputLayout.setError(null);
                                }
                            });
                            break;
                        case TypeField.TEL_NUMBER:
                            textInputEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                            textInputEditText.addTextChangedListener(new TextMask(new WeakReference<EditText>(textInputEditText)));
                            textInputEditText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    phone = s.toString();
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    textInputLayout.setError(null);
                                }
                            });
                            break;
                        default:
                            textInputEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                            textInputEditText.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    email = s.toString();
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    textInputLayout.setError(null);
                                }
                            });
                            break;
                    }

                    textInputLayout.addView(textInputEditText, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    textInputLayout.requestLayout();

                    params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    params.topToBottom = consForm.getChildAt(consForm.getChildCount() - 1).getId();
                    params.topMargin = dimenHelper.toPx(getResources(), Math.round(cell.getTopSpacing()));
                    consForm.addView(textInputLayout, params);

                    constraintSet = new ConstraintSet();
                    constraintSet.clone(consForm);
                    constraintSet.connect(textInputLayout.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(textInputLayout.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.applyTo(consForm);
                    break;
                case Type.TEXT:
                    TextView textView = new TextView(getContext());
                    textView.setTag(cell);
                    textView.setId(cell.getId());
                    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_field_contact_hint));
                    textView.setText(cell.getMessage());
                    textView.setTextSize(16);
                    textView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro-Medium.otf"));
                    params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    params.topMargin = dimenHelper.toPx(getResources(), Math.round(cell.getTopSpacing()));
                    consForm.addView(textView, params);
                    break;
                case Type.IMAGE:
                    break;
                case Type.CHECK_BOX:
                    CheckBox checkBox = new CheckBox(new ContextThemeWrapper(getContext(), R.style.CheckBox));
                    checkBox.setTag(cell);
                    checkBox.setId(cell.getId());
                    checkBox.setTextColor(ContextCompat.getColor(getContext(), R.color.grey_field_contact_hint));
                    checkBox.setTextSize(16);
                    checkBox.setText(cell.getMessage());
                    checkBox.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/DINPro-Medium.otf"));

                    params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    params.topToBottom = consForm.getChildAt(consForm.getChildCount() - 1).getId();
                    params.topMargin = dimenHelper.toPx(getResources(), Math.round(cell.getTopSpacing()));
                    consForm.addView(checkBox, params);
                    break;
                case Type.SEND:
                    Button button = new Button(new ContextThemeWrapper(getContext(), R.style.Button));
                    button.setTag(cell);
                    button.setBackgroundResource(R.drawable.shape_rectangle_rounded_color_primary);
                    button.setId(cell.getId());
                    button.setText(cell.getMessage());
                    button.setTextColor(Color.WHITE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            presenter.send(name, email, phone);
                        }
                    });

                    params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                    params.topToBottom = consForm.getChildAt(consForm.getChildCount() - 1).getId();
                    params.topMargin = dimenHelper.toPx(getResources(), Math.round(cell.getTopSpacing()));
                    consForm.addView(button, params);

                    constraintSet = new ConstraintSet();
                    constraintSet.clone(consForm);
                    constraintSet.connect(button.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
                    constraintSet.connect(button.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
                    constraintSet.applyTo(consForm);
                    break;
            }
        }
    }

    @Override
    public void showErrorName() {
        TextInputLayout textInputLayout = getFieldByType(TypeField.TEXT);
        textInputLayout.getEditText().setError("preencha o campo");
    }

    @Override
    public void showErrorEmail() {
        TextInputLayout textInputLayout = getFieldByType(TypeField.EMAIL);
        textInputLayout.getEditText().setError("email inválido");
    }

    @Override
    public void showErrorPhone() {
        TextInputLayout textInputLayout = getFieldByType(TypeField.TEL_NUMBER);
        textInputLayout.getEditText().setError("telefone inválido");
    }
    //endregion

    //region PRIVATE METHODS
    private TextInputLayout getFieldByType(@TypeField.TypeFieldCell int type) {
        for (int i = 0; i < consForm.getChildCount(); i++) {
            View view = consForm.getChildAt(i);
            Cell cell = (Cell) view.getTag();
            if (cell.getType().getId() == Type.FIELD && cell.getTypefield().getId() == type) {
                return (TextInputLayout) view;
            }
        }
        return null;
    }
    //endregion
    //endregion
}
