package com.carpinelli.testeandroid.ui.form;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.carpinelli.testeandroid.R;
import com.carpinelli.testeandroid.model.form.Cell;
import com.carpinelli.testeandroid.util.Mask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.VISIBLE;

public class FormFragment extends Fragment implements MvpForm.View {

    private static final String TAG = FormFragment.class.getSimpleName();

    @BindView(R.id.btn7)
    Button btnSend;

    @BindView(R.id.tv1)
    TextView tvTitle;

    @BindView(R.id.tv2)
    TextView tvNome;

    @BindView(R.id.tv4)
    TextView tvEmail;

    @BindView(R.id.tv6)
    TextView tvTelefone;

    @BindView(R.id.et2)
    EditText editNome;

    @BindView(R.id.et4)
    EditText editEmail;

    @BindView(R.id.et6)
    EditText editTelefone;

    @BindView(R.id.cb3)
    CheckBox checkBox;

    @BindView(R.id.fmForm)
    View fmForm;

    @BindView(R.id.fmSuccess)
    View fmSuccess;

    @BindView(R.id.tvSendNewMessage)
    TextView tvSendNewMessage;

    private FormPresenter formPresenter;

    private List<EditText> requiredFields = new ArrayList<>();

    public FormFragment() {

        this.formPresenter = new FormPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (formPresenter != null) {
            formPresenter.onStart();
        }
    }

    @Override
    public void onCellsReady(List<Cell> cells) {

        setFormLayout(cells);

        Log.d(TAG, "onCellsReady: " + cells.size());
    }

    @Override
    public void onSendForm() {

        openSuccessScreen();
    }

    public void setFormLayout(List<Cell> cells) {

        for (Cell cell : cells) {

            setupForm(cell);
        }

    }

    private void setupForm(Cell cell) {

        switch (cell.getId()) {
            case 1:
                tvTitle.setText(cell.getMessage());
                break;
            case 2:
                tvNome.setText(cell.getMessage());
                tvNome.setVisibility(cell.isHidden() ? View.GONE : VISIBLE);
                editNome.setVisibility(cell.isHidden() ? View.GONE : VISIBLE);
                if (cell.isRequired()) {
                    requiredFields.add(editNome);
                }
                break;
            case 3:
                checkBox.setText(cell.getMessage());
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            tvEmail.setVisibility(View.VISIBLE);
                            editEmail.setVisibility(View.VISIBLE);
                        } else {
                            tvEmail.setVisibility(View.GONE);
                            editEmail.setVisibility(View.GONE);
                        }
                    }
                });

                break;
            case 4:
                tvEmail.setText(cell.getMessage());
                tvEmail.setVisibility(cell.isHidden() ? View.GONE : VISIBLE);
                editEmail.setVisibility(cell.isHidden() ? View.GONE : VISIBLE);
                if (cell.isRequired() && checkBox.isChecked()) {
                    requiredFields.add(editEmail);
                }
                break;
            case 6:
                tvTelefone.setText(cell.getMessage());
                tvTelefone.setVisibility(cell.isHidden() ? View.GONE : VISIBLE);
                editTelefone.setVisibility(cell.isHidden() ? View.GONE : VISIBLE);
                editTelefone.addTextChangedListener(Mask.insertPhoneMask(editTelefone));
                if (cell.isRequired()) {
                    requiredFields.add(editTelefone);
                }
                break;
            case 7:
                btnSend.setText(cell.getMessage());
                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (checkFieldsRequired(requiredFields)) {
                            formPresenter.onFormCompleted();
                        }

                    }
                });
        }

    }

    private void openSuccessScreen() {

        fmForm.setVisibility(View.GONE);
        fmSuccess.setVisibility(View.VISIBLE);
    }

    private void openFormScreen() {

        fmSuccess.setVisibility(View.GONE);
        fmForm.setVisibility(View.VISIBLE);
    }

    private boolean checkFieldsRequired(List<EditText> requiredFields) {

        for (EditText editText : requiredFields) {

            Log.d(TAG, editText.getEditableText().toString());

            if (editText.getEditableText().toString().equals("")) {
                editText.setError("");
                return false;
            }

        }

        return true;
    }


    @OnClick(R.id.tvSendNewMessage)
    public void onClickSendNewMessage(View view) {

        openFormScreen();
    }


}
