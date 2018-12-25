package com.seletiva.santander.investment.ui.investments.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seletiva.santander.investment.R;
import com.seletiva.santander.investment.ui.investments.domain.InfoItem;

import java.util.List;

public class InvestmentsAdapter extends RecyclerView.Adapter<InvestmentViewHolder> {
    private List<InfoItem> data;

    public InvestmentsAdapter(List<InfoItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public InvestmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.details_cell, viewGroup, false);

        return new InvestmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InvestmentViewHolder holder, int position) {
        holder.bind(data.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
