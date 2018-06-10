package com.adenilson.testeandroid.investiment.ui.adapter.holder;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.investiment.model.Risk;
import com.adenilson.testeandroid.investiment.model.RiskEnum;
import com.adenilson.testeandroid.util.DimensUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class RiskViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_view_risk_title)
    TextView mTextViewRiskTitle;
    @BindView(R.id.view_lower)
    View mViewLower;
    @BindView(R.id.view_low)
    View mViewLow;
    @BindView(R.id.view_medium)
    View mViewMedium;
    @BindView(R.id.view_high)
    View mViewHigh;
    @BindView(R.id.view_higher)
    View mViewHigher;
    @BindView(R.id.image_view_indicator)
    ImageView mImageViewIndicator;
    @BindView(R.id.constraint_layout_risk)
    ConstraintLayout mConstraintLayout;

    public RiskViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindRisk(Risk risk){
        mTextViewRiskTitle.setText(risk.getRiskTitle());

        int riskLevel = risk.getRisk();
        if(riskLevel == RiskEnum.RISK_LOWER.risk){
            selectRiskLevel(mViewLower);
            setIndicator(mViewLow);
        }else if(riskLevel == RiskEnum.RISK_LOW.risk){
            selectRiskLevel(mViewLow);
            setIndicator(mViewLow);
        }else if(riskLevel == RiskEnum.RISK_MEDIUM.risk) {
            selectRiskLevel(mViewMedium);
            setIndicator(mViewMedium);
        }else if(riskLevel == RiskEnum.RISK_HIGH.risk){
            selectRiskLevel(mViewHigh);
            setIndicator(mViewHigh);
        }else if(riskLevel == RiskEnum.RISK_HIGHER.risk){
            selectRiskLevel(mViewHigher);
            setIndicator(mViewHigher);
        }

    }

    private void selectRiskLevel(View riskView) {
        ViewGroup.LayoutParams layoutParams = riskView.getLayoutParams();
        layoutParams.height = DimensUtil.dpToPx(riskView.getContext(), 16);
        riskView.setLayoutParams(layoutParams);
    }

    public void setIndicator(View riskView) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(mConstraintLayout);
        constraintSet.connect(mImageViewIndicator.getId(), ConstraintSet.LEFT, riskView.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(mImageViewIndicator.getId(), ConstraintSet.RIGHT, riskView.getId(), ConstraintSet.RIGHT, 0);
        constraintSet.connect(mImageViewIndicator.getId(), ConstraintSet.BOTTOM, riskView.getId(), ConstraintSet.TOP, 0);
        constraintSet.applyTo(mConstraintLayout);

    }
}
