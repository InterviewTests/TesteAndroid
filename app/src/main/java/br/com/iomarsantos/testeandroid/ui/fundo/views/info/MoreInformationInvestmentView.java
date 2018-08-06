package br.com.iomarsantos.testeandroid.ui.fundo.views.info;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import br.com.iomarsantos.testeandroid.R;
import br.com.iomarsantos.testeandroid.entity.InvestmentFund;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MoreInformationInvestmentView extends ConstraintLayout {

    @BindView(R.id.text_view_investimento_fundo_mes)
    TextView textViewFundoMes;

    @BindView(R.id.text_view_investimento_cdi_no_mes)
    TextView textViewCdiNoMes;

    @BindView(R.id.text_view_investimento_fundo_ano)
    TextView textViewFundoAno;

    @BindView(R.id.text_view_investimento_cdi_no_ano)
    TextView textViewCdiNoAno;

    @BindView(R.id.text_view_investimento_fundo_doze_meses)
    TextView textViewFundoDozeMees;

    @BindView(R.id.text_view_investimento_cdi_em_doze_meses)
    TextView textViewCdiEmDozeMeses;

    private InvestmentFund investmentFund;

    public MoreInformationInvestmentView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public MoreInformationInvestmentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public MoreInformationInvestmentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_more_information_investment, this);
        ButterKnife.bind(this, view);
    }

    public void setInvestmentFund(InvestmentFund investmentFund) {
        this.investmentFund = investmentFund;
        defineValues(investmentFund);
    }

    private void defineValues(InvestmentFund investmentFund) {
        defineMoreInfoMonth(investmentFund);
        defineMoreInfoYear(investmentFund);
        defineMoreInfoTwelveMonths(investmentFund);
    }

    private void defineMoreInfoTwelveMonths(InvestmentFund investmentFund) {
        textViewFundoDozeMees.setText(investmentFund.getMoreInfo().getTwelveMonths().getFundFormatted());
        textViewCdiEmDozeMeses.setText(investmentFund.getMoreInfo().getTwelveMonths().getCDIFormatted());
    }

    private void defineMoreInfoYear(InvestmentFund investmentFund) {
        textViewFundoAno.setText(investmentFund.getMoreInfo().getYear().getFundFormatted());
        textViewCdiNoAno.setText(investmentFund.getMoreInfo().getYear().getCDIFormatted());
    }

    private void defineMoreInfoMonth(InvestmentFund investmentFund) {
        textViewFundoMes.setText(investmentFund.getMoreInfo().getMonth().getFundFormatted());
        textViewCdiNoMes.setText(investmentFund.getMoreInfo().getMonth().getCDIFormatted());
    }
}
