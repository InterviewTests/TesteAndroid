package com.meteoro.testeandroid.ui.contact.presentation.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsModelType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsType;
import com.meteoro.testeandroid.ui.contact.domain.model.CellsViewModel;
import com.meteoro.testeandroid.ui.contact.domain.model.SendViewModel;
import com.meteoro.testeandroid.ui.contact.presentation.listener.OnSendClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendAdapterDelegate implements AdapterDelegate<CellsViewModel> {

    private OnSendClickListener listener;

    @Override
    public boolean isViewForData(CellsViewModel data, int position) {
        return getItem(data, position).getType() == CellsModelType.SEND;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contact_type_send_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CellsViewModel data, int position, RecyclerView.ViewHolder holder) {
        SendViewModel viewModel = (SendViewModel) getItem(data, position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.btnMessage.setText(viewModel.message());
        viewHolder.btnMessage.setOnClickListener(
                view -> callOnSendClickListener());

        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) viewHolder
                .btnMessage.getLayoutParams();
        params.setMargins(0, viewModel.topSpacing(), 0, 0);
    }

    private CellsType getItem(CellsViewModel data, int position) {
        return data.cellsTypeList().get(position);
    }

    private void callOnSendClickListener() {
        if (listener != null) {
            listener.onClick();
        }
    }

    public void setOnSendClickListener(OnSendClickListener listener) {
        this.listener = listener;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.btn_message)
        Button btnMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
