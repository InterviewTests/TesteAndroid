package com.meteoro.testeandroid.ui.investiment.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderInfoAdapterDelegate implements AdapterDelegate<ScreenViewModel> {

    @Override
    public boolean isViewForData(ScreenViewModel data, int position) {
        return true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.investiment_header_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScreenViewModel data, int position, RecyclerView.ViewHolder holder) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvTitle.setText(data.title());
        viewHolder.tvFundName.setText(data.fundName());
        viewHolder.tvWhatIs.setText(data.whatIs());
        viewHolder.tvDefinition.setText(data.definition());
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title)
        TextView tvTitle;

        @BindView(R.id.tv_fund_name)
        TextView tvFundName;

        @BindView(R.id.tv_what_is)
        TextView tvWhatIs;

        @BindView(R.id.tv_definition)
        TextView tvDefinition;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
