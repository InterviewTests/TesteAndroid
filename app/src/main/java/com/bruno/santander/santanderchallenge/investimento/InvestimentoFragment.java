package com.bruno.santander.santanderchallenge.investimento;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bruno.santander.santanderchallenge.BaseFragment;
import com.bruno.santander.santanderchallenge.R;
import com.bruno.santander.santanderchallenge.investimento.di.InjectInvestimento;
import com.bruno.santander.santanderchallenge.investimento.model.ScreenMapper;
import com.bruno.santander.santanderchallenge.investimento.presentation.InvestimentoContract;
import com.bruno.santander.santanderchallenge.investimento.presentation.InvestimentoPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestimentoFragment extends BaseFragment implements InvestimentoContract.View {

    public static final String TAG = InvestimentoFragment.class.getName();

    @BindView(R.id.coordinator_investimento)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @BindView(R.id.tv_fund_name)
    TextView tvFundName;

    @BindView(R.id.tv_what_is)
    TextView tvWhatIs;

    @BindView(R.id.tv_definition)
    TextView tvDefinition;

    @BindView(R.id.tv_risk_title)
    TextView tvRiskTitle;

    @BindView(R.id.graus_risco)
    LinearLayout linearGrausRisco;

    @BindView(R.id.tv_info_title)
    TextView tvInfoTitle;

    @BindView(R.id.tv_month_fund)
    TextView tvMonthFund;

    @BindView(R.id.tv_month_cdi)
    TextView tvMonthCdi;

    @BindView(R.id.tv_year_fund)
    TextView tvYearFund;

    @BindView(R.id.tv_year_cdi)
    TextView tvYearCdi;

    @BindView(R.id.tv_twelve_months_fund)
    TextView tvTwelveMonthsFund;

    @BindView(R.id.tv_twelve_months_cdi)
    TextView tvTwelveMonthsCdi;

    @BindView(R.id.info)
    LinearLayout linearInfo;

    private InvestimentoContract.Presenter presenter;

    public InvestimentoFragment() {
        // Required empty public constructor
    }

    public static InvestimentoFragment newInstance(){
        return new InvestimentoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_investimento, container, false);

        setUnBinder(ButterKnife.bind(this, view));

        InjectInvestimento.inject(getActivity(), this);
        presenter.getFund();

        return view;
    }

    @Override
    public void onDestroy() {
        if(presenter != null){
            presenter.dispose();
        }

        super.onDestroy();
    }

    @OnClick(R.id.bt_investir)
    public void btInvestir(){
//        TODO Investir
    }

    @Override
    public void onSuccessGetFund(ScreenMapper fund) {
        tvTitle.setText(fund.getScreen().getTitle());
        tvFundName.setText(fund.getScreen().getFundName());
        tvWhatIs.setText(fund.getScreen().getWhatIs());
        tvDefinition.setText(fund.getScreen().getDefinition());

        tvRiskTitle.setText(fund.getScreen().getRiskTitle());

        //View com os graus de risco
        UIGeneratorInvestimento.setGrauRisco(linearGrausRisco, fund.getScreen().getRisk());

        tvInfoTitle.setText(fund.getScreen().getInfoTitle());
        tvMonthFund.setText(getString(R.string.percentage_placeholder, String.valueOf(fund.getScreen().getMoreInfo().getMonth().getFund())));
        tvMonthCdi.setText(getString(R.string.percentage_placeholder, String.valueOf(fund.getScreen().getMoreInfo().getMonth().getCdi())));
        tvYearFund.setText(getString(R.string.percentage_placeholder, String.valueOf(fund.getScreen().getMoreInfo().getYear().getFund())));
        tvYearCdi.setText(getString(R.string.percentage_placeholder, String.valueOf(fund.getScreen().getMoreInfo().getYear().getCdi())));
        tvTwelveMonthsFund.setText(getString(R.string.percentage_placeholder, String.valueOf(fund.getScreen().getMoreInfo().getTwelveMonths().getFund())));
        tvTwelveMonthsCdi.setText(getString(R.string.percentage_placeholder, String.valueOf(fund.getScreen().getMoreInfo().getTwelveMonths().getCdi())));

        if(getActivity() != null)
            UIGeneratorInvestimento.createInfoLayout(getActivity(), linearInfo, fund.getScreen().getListInfo(), fund.getScreen().getDownInfoList());
    }

    @Override
    public void onSuccessInvestment() {
        Log.d(TAG, "Investment Successful");
    }

    @Override
    public void setPresenter(InvestimentoPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onError(String msg) {
        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_LONG).show();
    }
}
