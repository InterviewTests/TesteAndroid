package com.meteoro.testeandroid.ui.contact.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsModelType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.CheckboxViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckboxAdapterDelegate implements AdapterDelegate<CellsViewModel> {

    @Override
    public boolean isViewForData(CellsViewModel data, int position) {
        return getItem(data, position).getType() == CellsModelType.CHECKBOCK;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_type_checkbox_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CellsViewModel data, int position, RecyclerView.ViewHolder holder) {
        CheckboxViewModel viewModel = (CheckboxViewModel) getItem(data, position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.cgMessage.setText(viewModel.message());
    }

    private CellsType getItem(CellsViewModel data, int position) {
        return data.cellsTypeList().get(position);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cg_message)
        CheckBox cgMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
