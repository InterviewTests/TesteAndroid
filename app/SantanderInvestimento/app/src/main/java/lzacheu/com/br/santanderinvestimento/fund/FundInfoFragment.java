package lzacheu.com.br.santanderinvestimento.fund;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import lzacheu.com.br.santanderinvestimento.R;
import lzacheu.com.br.santanderinvestimento.model.fund.Screen;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class FundInfoFragment extends Fragment implements FundInfoContract.View, View.OnClickListener {

    private FundInfoContract.Presenter presenter;

    @BindView(R.id.screen_title)
    TextView screenTitleText;

    @BindView(R.id.screen_fund_name)
    TextView screenFundNameText;

    @BindView(R.id.screen_whatis)
    TextView screenWhatIsText;

    @BindView(R.id.screen_definition)
    TextView screenDefinitionText;

    @BindView(R.id.screen_risk_title)
    TextView screenRiskTitleText;

    @BindView(R.id.more_info_title)
    TextView moreInfoTitle;

    @BindView(R.id.more_info_month_fund)
    TextView moreInfoMonthFund;

    @BindView(R.id.more_info_month_cdi)
    TextView moreInfoMonthCdi;

    @BindView(R.id.more_info_year_fund)
    TextView moreInfoYearFund;

    @BindView(R.id.more_info_year_cdi)
    TextView moreInfoYearCdi;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public static FundInfoFragment newInstance() {
        return new FundInfoFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FundInfoPresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
        presenter.fetchData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fundinfo_fragment, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void setPresenter(FundInfoContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void bindValues(Screen screen) {
        screenTitleText.setText(screen.getTitle());
        screenFundNameText.setText(screen.getFundName());
        screenWhatIsText.setText(screen.getWhatIs());
        screenDefinitionText.setText(screen.getDefinition());
        screenRiskTitleText.setText(screen.getRiskTitle());
        moreInfoTitle.setText(screen.getInfoTitle());
        moreInfoMonthFund.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getMonth().getFund()));
        moreInfoMonthCdi.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getMonth().getCdi()));
        moreInfoYearFund.setText(String.format(Locale.getDefault(),"%.2f%%", screen.getMoreInfo().getYear().getFund()));
        moreInfoYearCdi.setText(String.format(Locale.getDefault(),"%.2f%%", screen.getMoreInfo().getYear().getCdi()));
//        moreInfoMonthFund.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getTwelveMonths().getFund()));
//        moreInfoMonthCdi.setText(String.format(Locale.getDefault(),"%.1f%%", screen.getMoreInfo().getTwelveMonths().getCdi()));


    }

    @Override
    public void onClick(View view) {

    }
}
