package com.meteoro.testeandroid.ui.investiment.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meteoro.testeandroid.R;
import com.meteoro.testeandroid.core.adapter.AdapterDelegate;
import com.meteoro.testeandroid.ui.investiment.domain.model.HeaderViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ModelType;
import com.meteoro.testeandroid.ui.investiment.domain.model.MoreInfoViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ScreenViewModel;
import com.meteoro.testeandroid.ui.investiment.domain.model.ViewModelType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderInfoAdapterDelegate implements AdapterDelegate<ScreenViewModel> {

    @Override
    public boolean isViewForData(ScreenViewModel data, int position) {
        return getItem(data, position).getType() == ViewModelType.HEADER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.investiment_header_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScreenViewModel data, int position, RecyclerView.ViewHolder holder) {
        HeaderViewModel viewModel = (HeaderViewModel) getItem(data, position);

        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvTitle.setText(viewModel.title());
        viewHolder.tvFundName.setText(viewModel.fundName());
        viewHolder.tvWhatIs.setText(viewModel.whatIs());
        viewHolder.tvDefinition.setText(viewModel.definition());
        viewHolder.tvRiskTitle.setText(viewModel.riskTitle());
        viewHolder.tvInfoTitle.setText(viewModel.infoTitle());

        MoreInfoViewModel moreInfoViewModel = viewModel.moreInfoViewModel();
        viewHolder.tvCdiMonth.setText(moreInfoViewModel.month().cdi());
        viewHolder.tvCdiYear.setText(moreInfoViewModel.year().cdi());
        viewHolder.tvCdi12Months.setText(moreInfoViewModel.months12().cdi());
    }

    private ModelType getItem(ScreenViewModel data, int position) {
        return data.modelTypeList().get(position);
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

        @BindView(R.id.tv_risk_title)
        TextView tvRiskTitle;

        @BindView(R.id.tv_info_title)
        TextView tvInfoTitle;

        @BindView(R.id.tv_cdi_month)
        TextView tvCdiMonth;

        @BindView(R.id.tv_cdi_year)
        TextView tvCdiYear;

        @BindView(R.id.tv_cdi_12months)
        TextView tvCdi12Months;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
