package com.nataliafavero.santander.ui.createContact;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.data.model.Cell;
import com.nataliafavero.santander.ui.fields.CellButton;
import com.nataliafavero.santander.ui.fields.CellCheckBox;
import com.nataliafavero.santander.ui.fields.CellTextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nataliafavero on 11/09/18.
 */

public class CreateContactFragment extends Fragment implements CreateContactContract.View {

    @BindView(R.id.relative_contact)
    RelativeLayout mRelative;

    @BindView(R.id.relative_contact_success)
    RelativeLayout mRelativeSucces;

    private CreateContactContract.Presenter mPresenter;
    private Button mButton;
    private List<CellTextInputLayout> listInputs;

    public static CreateContactFragment newInstance() {
        return new CreateContactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        listInputs = new ArrayList<>();
        mPresenter.start();
    }

    @Override
    public void setPresenter(CreateContactContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showCells(List<Cell> cellList) {
        int idToBelow = R.id.contact_view_title;

        for (final Cell cell: cellList) {

            switch (cell.getType()) {
                case 1:
                    listInputs.add(createEditText(cell, idToBelow));
                    break;
                case 4:
                    CellCheckBox checkBox = createCheckBox(cell, idToBelow);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            int idToShow = cell.getShow();
                            View view = mRelative.findViewById(idToShow);
                            if (isChecked) {
                                view.setVisibility(View.VISIBLE);
                            } else {
                                view.setVisibility(View.GONE);
                            }
                        }
                    });
                    break;
                case 5:
                    mButton = createButton(cell, idToBelow);
                    mButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            boolean isValid = true;
                            for (CellTextInputLayout cell: listInputs) {
                                if (cell.isErrorEnabled() && cell.isShown()) {
                                    isValid = false;
                                }
                            }
                            if (isValid) {
                                mRelative.setVisibility(View.GONE);
                                mRelativeSucces.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                    break;
            }

            idToBelow = cell.getId();
        }
    }

    @OnClick(R.id.contact_view_success_btn_send)
    public void onClickNewMessage() {
        mRelative.removeAllViews();
        mRelative.setVisibility(View.VISIBLE);
        mPresenter.start();
        mRelativeSucces.setVisibility(View.GONE);
    }

    private CellTextInputLayout createEditText(Cell cell, int idBelow) {
        CellTextInputLayout textInputLayout = new CellTextInputLayout(getContext());
        textInputLayout.setId(cell.getId());
        textInputLayout.setHint(cell.getMessage());
        textInputLayout.setInputType(getInputType(cell.getTypefield()));
        textInputLayout.setBelow(idBelow);
        textInputLayout.setMarginTop(cell.getTopSpacing());
        textInputLayout.setVisibility(getVisibility(cell.isHidden()));

        mRelative.addView(textInputLayout, textInputLayout.getLayoutParams());

        return textInputLayout;
    }

    private Button createButton(Cell cell, int idBelow) {
        CellButton button = new CellButton(getContext());
        button.setId(cell.getId());
        button.setBelow(idBelow);
        button.setText(cell.getMessage());
        button.setVisibility(getVisibility(cell.isHidden()));
        button.setMarginTop(cell.getTopSpacing());
        mRelative.addView(button);
        return button;
    }

    private CellCheckBox createCheckBox(Cell cell, int idBelow) {
        CellCheckBox checkBox = new CellCheckBox(getContext());
        checkBox.setId(cell.getId());
        checkBox.setText(cell.getMessage());
        checkBox.setBelow(idBelow);
        checkBox.setMarginTop(cell.getTopSpacing());
        checkBox.setVisibility(getVisibility(cell.isHidden()));
        mRelative.addView(checkBox);
        return checkBox;
    }

    private int getInputType(String inputType) {
        switch (inputType) {
            case "1":
            case "text":
                return InputType.TYPE_CLASS_TEXT;
            case "2":
            case "telNumber":
                return InputType.TYPE_CLASS_PHONE;
            case "3":
            case "email":
                return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
            default:
                return InputType.TYPE_CLASS_TEXT;
        }
    }

    private int getVisibility(boolean isHidden) {
        return isHidden ? View.GONE : View.VISIBLE;
    }
}
