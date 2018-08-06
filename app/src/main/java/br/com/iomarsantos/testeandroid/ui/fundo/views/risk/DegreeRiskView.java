package br.com.iomarsantos.testeandroid.ui.fundo.views.risk;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.iomarsantos.testeandroid.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DegreeRiskView extends ConstraintLayout {

    @BindView(R.id.view_degree_risk_level)
    View viewRisk;

    @BindView(R.id.image_view_degree_risk_indicator)
    ImageView imageViewIndicator;

    public DegreeRiskView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DegreeRiskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public DegreeRiskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_degree_risk, this);
        ButterKnife.bind(this, view);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ViewDegreeRisk, defStyleAttr, 0);

        try {
            int backgroundColor = a.getResourceId(R.styleable.ViewDegreeRisk_customBackground, 0);
            try {
                viewRisk.setBackgroundResource(backgroundColor);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            a.recycle();
        }
    }

    public void showIndicator(){
        this.imageViewIndicator.setVisibility(VISIBLE);
        ViewGroup.LayoutParams viewLayoutParams = viewRisk.getLayoutParams();
        viewLayoutParams.height = getResources().getDimensionPixelSize(R.dimen.degree_risk_active);
        viewRisk.setLayoutParams(viewLayoutParams);
    }

}
