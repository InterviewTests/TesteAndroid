package com.nataliafavero.santander.ui.createContact;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nataliafavero.santander.R;
import com.nataliafavero.santander.data.model.Cell;
import com.nataliafavero.santander.ui.fields.CellButton;
import com.nataliafavero.santander.ui.fields.CellCheckBox;
import com.nataliafavero.santander.ui.fields.CellTextInputLayout;
import com.nataliafavero.santander.ui.utils.Utils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nataliafavero on 11/09/18.
 */

public class CreateContactFragment extends Fragment implements CreateContactContract.View {

    @BindView(R.id.fragment_contact)
    RelativeLayout mRelative;

    private CreateContactContract.Presenter mPresenter;

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
        mPresenter.start();
    }

    @Override
    public void setPresenter(CreateContactContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showCells(List<Cell> cellList) {
        int idToBelow = R.id.contact_view_title;

        for (Cell cell: cellList) {

            switch (cell.getType()) {
                case 2:
                    createEditText(cell, idToBelow);
                    break;
                case 4:
                    createCheckBox(cell, idToBelow);
                    break;
                case 5:
                    createButton(cell, idToBelow);
                    break;
            }
        }
    }

    private TextInputLayout createEditText(Cell cell, int idBelow) {
        CellTextInputLayout textInputLayout = new CellTextInputLayout(getContext());
        textInputLayout.setHint(cell.getMessage());
        textInputLayout.setInputType(getInputType(cell.getTypefield()));
        textInputLayout.setBelow(idBelow);
        textInputLayout.setMarginTop(cell.getTopSpacing());
        textInputLayout.setVisibility(getVisibility(cell.isHidden()));

        mRelative.addView(textInputLayout, textInputLayout.getLayoutParams());

        idBelow = textInputLayout.getId();
        return textInputLayout;
    }

    private Button createButton(Cell cell, int idBelow) {
        CellButton button = new CellButton(getContext());
        button.setBelow(idBelow);
        button.setText(cell.getMessage());
        button.setVisibility(getVisibility(cell.isHidden()));
        mRelative.addView(button);
        idBelow = button.getId();
        return button;
    }

    private CheckBox createCheckBox(Cell cell, int idBelow) {
        CellCheckBox checkBox = new CellCheckBox(getContext());
        checkBox.setText(cell.getMessage());
        checkBox.setBelow(idBelow);
        checkBox.setMarginTop(cell.getTopSpacing());
        checkBox.setVisibility(getVisibility(cell.isHidden()));
        mRelative.addView(checkBox);
        idBelow = checkBox.getId();
        return checkBox;
    }

    private int getInputType(String inputType) {
        switch (inputType){
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
