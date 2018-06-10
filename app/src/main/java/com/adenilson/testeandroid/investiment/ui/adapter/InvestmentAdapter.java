package com.adenilson.testeandroid.investiment.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.investiment.ui.adapter.holder.DowInfoViewHolder;
import com.adenilson.testeandroid.investiment.ui.adapter.holder.HeaderViewHolder;
import com.adenilson.testeandroid.investiment.ui.adapter.holder.InfoViewHolder;
import com.adenilson.testeandroid.investiment.ui.adapter.holder.InvestViewHolder;
import com.adenilson.testeandroid.investiment.ui.adapter.holder.MoreInfoViewHolder;
import com.adenilson.testeandroid.investiment.ui.adapter.holder.RiskViewHolder;
import com.adenilson.testeandroid.investiment.ui.adapter.section.DowInfoSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.HeaderSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.InfoSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.InvestmentSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.MoreInfoSection;
import com.adenilson.testeandroid.investiment.ui.adapter.section.RiskSection;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 09/06/2018
 */

public class InvestmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnInvestmentClickListener mListener;
    private List<InvestmentSection> mSections;

    public InvestmentAdapter(OnInvestmentClickListener listener) {
        this.mSections = new ArrayList<>();
        this.mListener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case InvestmentSection.TYPE_HEADER:
                View view = inflater.inflate(R.layout.item_header, parent, false);
                return new HeaderViewHolder(view);
            case InvestmentSection.TYPE_RISK:
                view = inflater.inflate(R.layout.item_risk, parent, false);
                return new RiskViewHolder(view);
            case InvestmentSection.TYPE_MORE_INFO:
                view = inflater.inflate(R.layout.item_more_info, parent, false);
                return new MoreInfoViewHolder(view);
            case InvestmentSection.TYPE_INFO:
                view = inflater.inflate(R.layout.item_info, parent, false);
                return new InfoViewHolder(view);
            case InvestmentSection.TYPE_DOW_INFO:
                view = inflater.inflate(R.layout.item_dow_info, parent, false);
                return new DowInfoViewHolder(view);
            case InvestmentSection.TYPE_INVEST:
                view = inflater.inflate(R.layout.item_invest, parent, false);
                return new InvestViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderSection headerSection = (HeaderSection) mSections.get(position);
            ((HeaderViewHolder) holder).bindHeader(headerSection.getHeader(), mListener);
        } else if (holder instanceof RiskViewHolder) {
            RiskSection riskSection = (RiskSection) mSections.get(position);
            ((RiskViewHolder) holder).bindRisk(riskSection.getRisk());
        } else if(holder instanceof MoreInfoViewHolder){
            MoreInfoSection moreInfoSection = (MoreInfoSection) mSections.get(position);
            ((MoreInfoViewHolder)holder).bindMoreInfo(moreInfoSection.getMoreInfo());
        }else if(holder instanceof InfoViewHolder){
            InfoSection infoSection = (InfoSection) mSections.get(position);
            ((InfoViewHolder)holder).bindInfo(infoSection.getInfo());
        }else if(holder instanceof  DowInfoViewHolder){
            DowInfoSection dowInfoSection = (DowInfoSection) mSections.get(position);
            ((DowInfoViewHolder)holder).bindDowInfo(dowInfoSection.getInfo());
        }else if(holder instanceof InvestViewHolder){
            ((InvestViewHolder)holder).bindInvest(mListener);
        }

    }

    @Override
    public int getItemCount() {
        return mSections.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mSections.get(position).getItemViewType();
    }

    public void setData(List<InvestmentSection> data) {
        this.mSections = data;
        notifyDataSetChanged();
    }

    public List<InvestmentSection> getData() {
        return mSections;
    }


    public interface OnInvestmentClickListener {

        void onInvestClick();

        void onShareReportClick(String report);
    }
}
