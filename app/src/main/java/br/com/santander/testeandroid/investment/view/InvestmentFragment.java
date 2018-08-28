package br.com.santander.testeandroid.investment.view;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

import br.com.santander.testeandroid.R;
import br.com.santander.testeandroid.base.BaseFragment;
import br.com.santander.testeandroid.investment.InvestmentContract;
import br.com.santander.testeandroid.investment.model.BaseInfo;
import br.com.santander.testeandroid.investment.model.MoreInfo;
import br.com.santander.testeandroid.investment.presenter.InvestmentPresenter;

public class InvestmentFragment extends BaseFragment<InvestmentPresenter>
        implements InvestmentContract {

    private static InvestmentFragment instance;

    @NonNull
    @Override
    protected InvestmentPresenter createPresenter() {
        return new InvestmentPresenter(this);
    }

    public static InvestmentFragment getInstance() {
        if (instance == null)
            instance = new InvestmentFragment();

        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_investment, container, false);
    }

    @Override
    public void showLoading() {
        findViewById(R.id.view_loading).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.view_loading).setVisibility(View.GONE);
    }

    @Override
    public void setHeaderInfo(String title, String fundName, String subtitle, String definition) {
        getTextView(R.id.investment_label).setText(title);
        getTextView(R.id.fund_name).setText(fundName);
        getTextView(R.id.subtitle_label).setText(subtitle);
        getTextView(R.id.definition_label).setText(definition);
    }

    @Override
    public void setRiskInfo(String title, Integer risk) {
        getTextView(R.id.risk_title).setText(title);

        View colorIndicator = null;
        ImageView arrowIndicator = null;
        switch (risk) {
            case 1:
                colorIndicator = findViewById(R.id.view_lower);
                arrowIndicator = getImageView(R.id.iv_lower);
                break;
            case 2:
                colorIndicator = findViewById(R.id.view_low);
                arrowIndicator = getImageView(R.id.iv_low);
                break;
            case 3:
                colorIndicator = findViewById(R.id.view_medium);
                arrowIndicator = getImageView(R.id.iv_medium);
                break;
            case 4:
                colorIndicator = findViewById(R.id.view_high);
                arrowIndicator = getImageView(R.id.iv_high);
                break;
            case 5:
                colorIndicator = findViewById(R.id.view_higher);
                arrowIndicator = getImageView(R.id.iv_higher);
                break;
        }

        if (colorIndicator != null && arrowIndicator != null)
            setRisk(colorIndicator, arrowIndicator);

    }

    @Override
    public void setMoreInfo(String title, MoreInfo moreInfo) {
        getTextView(R.id.info_title).setText(title);
        setPercentageField(R.id.month_fund, moreInfo.getMonth().getFund());
        setPercentageField(R.id.month_cdi, moreInfo.getMonth().getCdi());
        setPercentageField(R.id.year_fund, moreInfo.getYear().getFund());
        setPercentageField(R.id.year_cdi, moreInfo.getYear().getCdi());
        setPercentageField(R.id.twelve_months_fund, moreInfo.getTwelveMonths().getFund());
        setPercentageField(R.id.twelve_months_cdi, moreInfo.getTwelveMonths().getCdi());
    }

    @Override
    public void setListInfo(List<BaseInfo> infoList) {
        getRecyclerView(R.id.recycler_info).setAdapter(new InfoAdapter(infoList));
        getRecyclerView(R.id.recycler_info).setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void setInvestmentListener() {
        findViewById(R.id.investment_button).setOnClickListener(new OnInvestmentButtonsClicked());
    }

    @Override
    public void showContainer() {
        findViewById(R.id.container_investment).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContainer() {
        findViewById(R.id.container_investment).setVisibility(View.GONE);
    }

    @Override
    public void showError() {
        findViewById(R.id.view_error).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        findViewById(R.id.view_error).setVisibility(View.GONE);
    }

    private void setPercentageField(@IdRes int id, Double percent) {
        getTextView(id).setText(String.format("%.2f%%", percent));
    }

    private void setRisk(View colorIndicator, ImageView arrowIndicator) {
        colorIndicator.setLayoutParams(getLayoutParams(colorIndicator));
        arrowIndicator.setImageResource(R.drawable.ic_arrow_down);
    }

    @NonNull
    private LinearLayout.LayoutParams getLayoutParams(View colorIndicator) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) colorIndicator.getLayoutParams();
        params.height = 40;
        return params;
    }

    private class OnInvestmentButtonsClicked implements View.OnClickListener {
        @Override
        public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.investment_button:
                    getPresenter().onInvestmentAction();
                    break;
            }
        }
    }

}
