package com.meteoro.testeandroid.ui.investiment.presentation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.meteoro.testeandroid.R;

public class RiskView extends LinearLayout {

    private ImageView ivRisk1;
    private ImageView ivRisk2;
    private ImageView ivRisk3;
    private ImageView ivRisk4;
    private ImageView ivRisk5;

    public RiskView(Context context) {
        super(context);
        if (isInEditMode()) {
            return;
        }

        initialize(null);
    }

    public RiskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (isInEditMode()) {
            return;
        }

        initialize(attrs);
    }

    public RiskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (isInEditMode()) {
            return;
        }

        initialize(attrs);
    }

    private void initialize(AttributeSet attrs) {
        inflate(getContext(), R.layout.risk_view, this);

        ivRisk1 = findViewById(R.id.iv_risk_1);
        ivRisk2 = findViewById(R.id.iv_risk_2);
        ivRisk3 = findViewById(R.id.iv_risk_3);
        ivRisk4 = findViewById(R.id.iv_risk_4);
        ivRisk5 = findViewById(R.id.iv_risk_5);

        setAllInvisible();
    }

    private void setAllInvisible() {
        ivRisk1.setVisibility(INVISIBLE);
        ivRisk2.setVisibility(INVISIBLE);
        ivRisk3.setVisibility(INVISIBLE);
        ivRisk4.setVisibility(INVISIBLE);
        ivRisk5.setVisibility(INVISIBLE);
    }

    public void setRisk(int risk) {
        switch (risk) {
            case 1:
                setAllInvisible();
                ivRisk1.setVisibility(VISIBLE);
                break;
            case 2:
                setAllInvisible();
                ivRisk2.setVisibility(VISIBLE);
                break;
            case 3:
                setAllInvisible();
                ivRisk3.setVisibility(VISIBLE);
                break;
            case 4:
                setAllInvisible();
                ivRisk4.setVisibility(VISIBLE);
                break;
            case 5:
                setAllInvisible();
                ivRisk5.setVisibility(VISIBLE);
                break;
            default:
                setAllInvisible();
                ivRisk1.setVisibility(VISIBLE);
        }
    }
}
