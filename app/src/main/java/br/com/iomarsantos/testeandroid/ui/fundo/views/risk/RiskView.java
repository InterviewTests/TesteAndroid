package br.com.iomarsantos.testeandroid.ui.fundo.views.risk;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.InvestmentFund;
import br.com.iomarsantos.testeandroid.entity.RiskLevel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RiskView extends ConstraintLayout {

    @BindView(R.id.text_view_investimento_titulo_risco)
    TextView textViewTituloRisco;

    @BindView(R.id.degree_risk_view_investimento_one)
    DegreeRiskView degreeRiskViewLevelOne;

    @BindView(R.id.degree_risk_view_investimento_two)
    DegreeRiskView degreeRiskViewLevelTwo;

    @BindView(R.id.degree_risk_view_investimento_three)
    DegreeRiskView degreeRiskViewLevelthree;

    @BindView(R.id.degree_risk_view_investimento_four)
    DegreeRiskView degreeRiskViewLevelFour;

    @BindView(R.id.degree_risk_view_investimento_five)
    DegreeRiskView degreeRiskViewLevelFive;

    public RiskView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public RiskView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RiskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_risk, this);
        ButterKnife.bind(this, view);
    }

    public void setInvestmentFund(InvestmentFund investmentFund) {
        defineRiskTitle(investmentFund);
        defineRisk(investmentFund);
    }

    private void defineRiskTitle(InvestmentFund investmentFund) {
        this.textViewTituloRisco.setText(investmentFund.getRiskTitle());
    }

    private void defineRisk(InvestmentFund investmentFund) {
        switch (investmentFund.getRisk()) {
            case RiskLevel.LEVEL_ONE:
                degreeRiskViewLevelOne.showIndicator();
                break;
            case RiskLevel.LEVEL_TWO:
                degreeRiskViewLevelTwo.showIndicator();
                break;
            case RiskLevel.LEVEL_THREE:
                degreeRiskViewLevelthree.showIndicator();
                break;
            case RiskLevel.LEVEL_FOUR:
                degreeRiskViewLevelFour.showIndicator();
                break;
            case RiskLevel.LEVEL_FIVE:
                degreeRiskViewLevelFive.showIndicator();
                break;
        }
    }

}
