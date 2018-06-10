package com.adenilson.testeandroid.investiment.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.investiment.ui.adapter.InvestmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class InvestViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.button_invest)
    Button mButtonInvest;

    public InvestViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindInvest(final InvestmentAdapter.OnInvestmentClickListener listener){
        mButtonInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onInvestClick();
            }
        });
    }
}
