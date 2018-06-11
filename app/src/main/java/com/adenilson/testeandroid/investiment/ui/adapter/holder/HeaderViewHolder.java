package com.adenilson.testeandroid.investiment.ui.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.investiment.model.Header;
import com.adenilson.testeandroid.investiment.ui.adapter.InvestmentAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class HeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_view_share)
    ImageView mImageViewShare;
    @BindView(R.id.text_view_title)
    TextView mTextViewTitle;
    @BindView(R.id.text_view_name)
    TextView mTextViewName;
    @BindView(R.id.text_view_what_is)
    TextView mTextViewWhatIs;
    @BindView(R.id.text_view_definition)
    TextView mTextViewDefinition;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindHeader(Header header, final InvestmentAdapter.OnInvestmentClickListener listener) {
        mImageViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onShareReportClick(itemView.getContext().getString(R.string.report));
            }
        });
        mTextViewTitle.setText(header.getTitle());
        mTextViewName.setText(header.getFundName());
        mTextViewWhatIs.setText(header.getWhatIs());
        mTextViewDefinition.setText(header.getDefinition());
    }
}
