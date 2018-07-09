package com.meteoro.testeandroid.ui.investiment.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.investiment.domain.model.DownInfoViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ModelType;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ViewModelType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DownInfoAdapterDelegate implements AdapterDelegate<ScreenViewModel> {

    @Override
    public boolean isViewForData(ScreenViewModel data, int position) {
        return getItem(data, position).getType() == ViewModelType.DOWN_INFO;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.investiment_down_info_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScreenViewModel data, int position, RecyclerView.ViewHolder holder) {
        DownInfoViewModel viewModel = (DownInfoViewModel) getItem(data, position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvDownInfoName.setText(viewModel.name());
    }

    private ModelType getItem(ScreenViewModel data, int position) {
        return data.modelTypeList().get(position);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_down_info_name)
        TextView tvDownInfoName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
