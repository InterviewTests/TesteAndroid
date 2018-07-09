package com.meteoro.testeandroid.ui.contact.presentation.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsModelType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.FieldViewModel;
import com.meteoro.testeandroid.ui.contact.presentation.listener.BrPhoneNumberFormatter;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FieldAdapterDelegate implements AdapterDelegate<CellsViewModel> {

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
        viewHolder.tieMessage.setHint(viewModel.message());

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) viewHolder
                .tilMessage.getLayoutParams();
        params.setMargins(0, viewModel.topSpacing(), 0, 0);

        viewHolder.tieMessage.setInputType(InputType.TYPE_CLASS_PHONE);
        BrPhoneNumberFormatter formatter = new BrPhoneNumberFormatter(new WeakReference<>(viewHolder.tieMessage));
        viewHolder.tieMessage.addTextChangedListener(formatter);
    }

    private CellsType getItem(CellsViewModel data, int position) {
        return data.cellsTypeList().get(position);
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
