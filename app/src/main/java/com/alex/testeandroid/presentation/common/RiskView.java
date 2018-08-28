package com.alex.testeandroid.presentation.common;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.alex.testeandroid.R;
import com.alex.testeandroid.presentation.helpers.DimenHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alex on 28/08/18.
 */
public class RiskView extends ConstraintLayout {

    //region FIELDS
    @BindView(R.id.custom_risk_view_img_scale_risk_1)
    ImageView imgRisk1;
    @BindView(R.id.custom_risk_view_img_scale_risk_2)
    ImageView imgRisk2;
    @BindView(R.id.custom_risk_view_img_scale_risk_3)
    ImageView imgRisk3;
    @BindView(R.id.custom_risk_view_img_scale_risk_4)
    ImageView imgRisk4;
    @BindView(R.id.custom_risk_view_img_scale_risk_5)
    ImageView imgRisk5;
    @BindView(R.id.custom_risk_view_vw_scale_risk_1)
    View vwRisk1;
    @BindView(R.id.custom_risk_view_vw_scale_risk_2)
    View vwRisk2;
    @BindView(R.id.custom_risk_view_vw_scale_risk_3)
    View vwRisk3;
    @BindView(R.id.custom_risk_view_vw_scale_risk_4)
    View vwRisk4;
    @BindView(R.id.custom_risk_view_vw_scale_risk_5)
    View vwRisk5;
    //endregion

    //region CONSTRUCTOR
    public RiskView(Context context) {
        this(context, null);
    }

    public RiskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RiskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    //endregion

    //region METHODS
    //region PUBLIC METHODS
    public void selectRisk(int risk) {
        int height = new DimenHelper().toPx(getResources(), 14);
        switch (risk) {
            case 1:
                imgRisk1.setVisibility(View.VISIBLE);
                vwRisk1.getLayoutParams().height = height;
                vwRisk1.requestLayout();
                break;
            case 2:
                imgRisk2.setVisibility(View.VISIBLE);
                vwRisk2.getLayoutParams().height = height;
                vwRisk2.requestLayout();
                break;
            case 3:
                imgRisk3.setVisibility(View.VISIBLE);
                vwRisk3.getLayoutParams().height = height;
                vwRisk3.requestLayout();
                break;
            case 4:
                imgRisk4.setVisibility(View.VISIBLE);
                vwRisk4.getLayoutParams().height = height;
                vwRisk4.requestLayout();
                break;
            default:
                imgRisk5.setVisibility(View.VISIBLE);
                vwRisk5.getLayoutParams().height = height;
                vwRisk5.requestLayout();
                break;
        }
    }
    //endregion

    //region PRIVATE METHODS
    private void init() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_risk_view, this);

        ButterKnife.bind(this, this);
    }
    //endregion
    //endregion
}
