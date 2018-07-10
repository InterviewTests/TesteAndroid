package com.meteoro.testeandroid.ui.investiment.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.investiment.domain.model.InfoViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ModelType;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ViewModelType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoAdapterDelegate implements AdapterDelegate<ScreenViewModel> {

    @Override
    public boolean isViewForData(ScreenViewModel data, int position) {
        return getItem(data, position).getType() == ViewModelType.INFO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.investiment_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScreenViewModel data, int position, RecyclerView.ViewHolder holder) {
        InfoViewModel viewModel = (InfoViewModel) getItem(data, position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvInfoName.setText(viewModel.name());
        viewHolder.tvInfoValue.setText(viewModel.data());
    }

    private ModelType getItem(ScreenViewModel data, int position) {
        return data.modelTypeList().get(position);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_info_name)
        TextView tvInfoName;

        @BindView(R.id.tv_info_value)
        TextView tvInfoValue;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
