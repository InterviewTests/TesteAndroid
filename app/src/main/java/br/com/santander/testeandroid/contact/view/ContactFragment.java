package br.com.santander.testeandroid.contact.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.base.BaseFragment;
import br.com.santander.testeandroid.contact.ContactContract;
import br.com.santander.testeandroid.contact.model.CellResponse;
import br.com.santander.testeandroid.contact.model.CellsResponse;
import br.com.santander.testeandroid.contact.presenter.ContactPresenter;
import br.com.santander.testeandroid.utils.CellContract;
import br.com.santander.testeandroid.utils.CellGenerator;
import br.com.santander.testeandroid.utils.Constants;
import br.com.santander.testeandroid.utils.UIUtils;
import br.com.santander.testeandroid.utils.ValidatorUtils;
import br.com.santander.testeandroid.utils.custom.ButtonCell;
import br.com.santander.testeandroid.utils.custom.CheckBoxCell;
import br.com.santander.testeandroid.utils.custom.EditTextCell;
import br.com.santander.testeandroid.utils.custom.ImageViewCell;
import br.com.santander.testeandroid.utils.custom.TextViewCell;

public class ContactFragment extends BaseFragment<ContactPresenter> implements ContactContract {

    private static ContactFragment instance;
    private ConstraintLayout constraintLayout;

    @NonNull
    @Override
    protected ContactPresenter createPresenter() {
        return new ContactPresenter(this);
    }

    public static ContactFragment getInstance() {
        if (instance == null)
            instance = new ContactFragment();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contact, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        configureEvents();
    }

    private void configureEvents() {
        getRootView().findViewById(R.id.lSuccess_tvSend)
                .setOnClickListener(new OnSendNewMessageClick());
    }

    @Override
    public void clearForm() {
        List<EditText> textList = getViewsToValidate();

        for (EditText editText : textList) {
            editText.setText("");
        }
    }

    @Override
    public void showSuccess() {
        findViewById(R.id.view_success).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideSuccess() {
        findViewById(R.id.view_success).setVisibility(View.GONE);
    }

    @Override
    public void showLoading() {
        findViewById(R.id.view_loading).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.view_loading).setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        findViewById(R.id.view_error).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        findViewById(R.id.view_error).setVisibility(View.GONE);
    }

    @Override
    public void configureUI(CellsResponse response) {
        List<CellResponse> cells = response.getCells();

        for (CellResponse cell : cells) {
            int index = cells.indexOf(cell);
            int startID = cell.getId();
            int endID = getFormContainer().getId();
            boolean isFirstElement = true;

            if (index > 0) {
                CellResponse previous = cells.get(index - 1);
                endID = previous.getId();
                isFirstElement = false;
            }

            addField(cell);
            UIUtils.defineConstraints(getFormContainer(), cell, startID, endID, isFirstElement);
        }
    }

    private void addField(CellResponse cell) {
        CellContract cellContract = null;

        switch (cell.getType()) {
            case Constants.TYPE_FIELD:
                cellContract = new EditTextCell(getContext());
                break;
            case Constants.TYPE_TEXT:
                cellContract = new TextViewCell(getContext());
                break;
            case Constants.TYPE_IMAGE:
                cellContract = new ImageViewCell(getContext());
                break;
            case Constants.TYPE_CHECKBOX:
                cellContract = new CheckBoxCell(getContext(), new OnEmailCheckedChanged());
                break;
            case Constants.TYPE_SEND:
                cellContract = new ButtonCell(getContext(), new OnSendEventClick());
                break;
        }

        if (cellContract != null) {
            CellGenerator generator = new CellGenerator(cellContract);
            View view = generator.createCell(cell);
            getFormContainer().addView(view);
        }

    }

    private void setVisibilityTo(int visibility) {
        int childCount = getFormContainer().getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = getFormContainer().getChildAt(i);

            if (view instanceof EditText) {
                EditText editText = (EditText) view;

                if (editText.getInputType() == InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS) {
                    editText.setVisibility(visibility);

                    if (visibility == View.VISIBLE) {
                        editText.requestFocus();
                    }
                }
            }
        }

    }

    private class OnEmailCheckedChanged implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            if (isChecked) {
                setVisibilityTo(View.VISIBLE);
            } else {
                setVisibilityTo(View.GONE);
            }
        }
    }

    private class OnSendEventClick implements View.OnClickListener {
        @Override
        public void onClick(final View view) {

            if (ValidatorUtils.validadeFields(getViewsToValidate())) {
                showSuccess();
                clearForm();
            }

        }
    }

    private List<EditText> getViewsToValidate() {
        int childCount = getFormContainer().getChildCount();
        List<EditText> edts = new ArrayList<>();

        for (int i = 0; i < childCount; i++) {
            View v = getFormContainer().getChildAt(i);

            if (v instanceof EditText && v.getVisibility() == View.VISIBLE) {
                edts.add((EditText) v);
            }

        }

        return edts;
    }

    private class OnSendNewMessageClick implements View.OnClickListener {
        @Override
        public void onClick(final View view) {
            clearForm();
            hideSuccess();
        }
    }

    private ConstraintLayout getFormContainer() {
        if (constraintLayout == null) {
            constraintLayout = (ConstraintLayout) getActivity().findViewById(R.id.form_container);
        }
        return constraintLayout;
    }


}
