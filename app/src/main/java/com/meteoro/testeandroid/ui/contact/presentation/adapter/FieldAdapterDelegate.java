package com.meteoro.testeandroid.ui.contact.presentation.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsModelType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.FieldViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.TypeField;
import com.meteoro.testeandroid.ui.contact.presentation.listener.BrPhoneNumberFormatter;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FieldAdapterDelegate implements AdapterDelegate<CellsViewModel> {

    private BrPhoneNumberFormatter numberFormatter;

    @Override
    public boolean isViewForData(CellsViewModel data, int position) {
        return getItem(data, position).getType() == CellsModelType.FIELD;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_type_field_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CellsViewModel data, int position, RecyclerView.ViewHolder holder) {
        FieldViewModel viewModel = (FieldViewModel) getItem(data, position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tilMessage.setHint(viewModel.message());

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) viewHolder
                .tilMessage.getLayoutParams();
        params.setMargins(0, viewModel.topSpacing(), 0, 0);

        setInputType(viewHolder.tieMessage, viewModel.typeField());

        viewHolder.tieMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.valueField(s.toString());
                viewHolder.tilMessage.setError(null);
            }
        });

        viewHolder.tieMessage.setText(viewModel.valueField());
        setFieldValid(viewHolder.tilMessage, viewModel);
    }

    private CellsType getItem(CellsViewModel data, int position) {
        return data.cellsTypeList().get(position);
    }

    private void setInputType(TextInputEditText editText, TypeField typeField) {
        switch (typeField) {
            case TEXT:
                editText.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            case EMAIL:
                editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;
            case TEL_NUMBER:
                editText.setInputType(InputType.TYPE_CLASS_PHONE);
                numberFormatter = new BrPhoneNumberFormatter(new WeakReference<>(editText));
                editText.addTextChangedListener(numberFormatter);
                break;
        }
    }

    private void setFieldValid(TextInputLayout inputLayout, FieldViewModel viewModel) {
        if (viewModel.validated() && viewModel.isNotValid()) {
            String invalidText = inputLayout.getContext().getString(R.string.fragment_contact_field_invalid);
            inputLayout.setError(invalidText);
        }
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.til_message)
        TextInputLayout tilMessage;

        @BindView(R.id.tie_message)
        TextInputEditText tieMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
