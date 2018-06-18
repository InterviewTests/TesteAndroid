package lzacheu.com.br.santanderinvestimento.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import lzacheu.com.br.santanderinvestimento.R;

/**
 * Created by luiszacheu on 6/18/18.
 */

public class RiskLayout extends LinearLayout {

    private static final int SIZE_HIGHLIGHT_SELECTED = 30;

    Context context;
    private ImageView arrowPointLowRisk;
    private ImageView arrowPointLowMRisk;
    private ImageView arrowPointMediumRisk;
    private ImageView arrowPointMediumMRisk;
    private ImageView arrowPointHighRisk;

    private View lowRiskHighlight;
    private View lowMRiskHighlight;
    private View mediumRiskHighlight;
    private View mediumMRiskHighlight;
    private View highRiskHighlight;

    public RiskLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public RiskLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public RiskLayout(Context context) {
        super(context);
        this.context = context;
        init();
    }


    private void init() {
        LayoutInflater.from(context).inflate(R.layout.risk_element_view, this);
        arrowPointLowRisk = findViewById(R.id.arrow_point_low_risk);
        arrowPointLowMRisk = findViewById(R.id.arrow_point_low_m_risk);
        arrowPointMediumRisk = findViewById(R.id.arrow_point_medium_risk);
        arrowPointMediumMRisk = findViewById(R.id.arrow_point_medium_m_risk);
        arrowPointHighRisk = findViewById(R.id.arrow_point_high_risk);

        lowRiskHighlight = findViewById(R.id.low_risk_highlight);
        lowMRiskHighlight = findViewById(R.id.low_m_risk_highlight);
        mediumRiskHighlight = findViewById(R.id.medium_risk_highlight);
        mediumMRiskHighlight = findViewById(R.id.medium_m_risk_highlight);
        highRiskHighlight = findViewById(R.id.high_risk_highlight);
    }


    public void setRisk(int riskSelected) {
        switch (riskSelected) {
            case 1:
                arrowPointLowRisk.setVisibility(VISIBLE);
                LinearLayout.LayoutParams paramsLow = (LinearLayout.LayoutParams) lowRiskHighlight.getLayoutParams();
                paramsLow.height = SIZE_HIGHLIGHT_SELECTED;
                lowRiskHighlight.setLayoutParams(paramsLow);
                break;
            case 2:
                arrowPointLowMRisk.setVisibility(VISIBLE);
                LinearLayout.LayoutParams paramsLowm = (LinearLayout.LayoutParams) lowMRiskHighlight.getLayoutParams();
                paramsLowm.height = SIZE_HIGHLIGHT_SELECTED;
                lowMRiskHighlight.setLayoutParams(paramsLowm);
                break;
            case 3:
                arrowPointMediumRisk.setVisibility(VISIBLE);
                LinearLayout.LayoutParams paramsMed = (LinearLayout.LayoutParams) mediumRiskHighlight.getLayoutParams();
                paramsMed.height = SIZE_HIGHLIGHT_SELECTED;
                mediumRiskHighlight.setLayoutParams(paramsMed);
                break;
            case 4:
                arrowPointMediumMRisk.setVisibility(VISIBLE);
                LinearLayout.LayoutParams paramsMedM = (LinearLayout.LayoutParams) mediumMRiskHighlight.getLayoutParams();
                paramsMedM.height = SIZE_HIGHLIGHT_SELECTED;
                mediumMRiskHighlight.setLayoutParams(paramsMedM);
                break;
            case 5:
                arrowPointHighRisk.setVisibility(VISIBLE);
                LinearLayout.LayoutParams paramsHigh = (LinearLayout.LayoutParams) highRiskHighlight.getLayoutParams();
                paramsHigh.height = SIZE_HIGHLIGHT_SELECTED;
                highRiskHighlight.setLayoutParams(paramsHigh);
                break;
        }
    }

}
