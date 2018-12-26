package com.seletiva.santander.investment.ui.investments.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.ui.investments.domain.InfoItem;

class InvestmentViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView value;
    private LinearLayout downloadIndicator;

    InvestmentViewHolder(View view) {
        super(view);

        title = view.findViewById(R.id.itemName);
        value = view.findViewById(R.id.itemValue);
        downloadIndicator = view.findViewById(R.id.downloadIndicator);
    }

    void bind(InfoItem item) {
        title.setText(item.getName());

        if (item.getData() == null) {
            value.setVisibility(View.GONE);
            downloadIndicator.setVisibility(View.VISIBLE);
        } else {
            value.setVisibility(View.VISIBLE);
            value.setText(item.getData());
            downloadIndicator.setVisibility(View.GONE);
        }
    }

}